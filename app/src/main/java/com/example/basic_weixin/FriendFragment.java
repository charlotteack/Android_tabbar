package com.example.basic_weixin;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends Fragment {
    private List<Friend> friendList = new ArrayList<>();
    private MyDAO myDAO;  //数据库访问对象
    private  String selId = null;  //选择项id
    private FriendAdapter adapter;

    public FriendFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initFriend() {
        myDAO = new MyDAO(getContext());  //创建数据库访问对象
        if(myDAO.getRecordsNumber()==0) {  //防止重复运行时重复插入记录
            myDAO.insertInfo("hzx", "男", 20);   //插入记录
            myDAO.insertInfo("jky", "女", 20); //插入记录
        }
        displayRecords();
    }

    public void displayRecords(){  //显示记录方法定义
        Cursor cursor = myDAO.allQuery();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String sex=cursor.getString(cursor.getColumnIndex("sex"));
            int age=cursor.getInt(cursor.getColumnIndex("age"));//推荐此种方式
            Friend friend = new Friend(name, sex, age);
            friendList.add(friend);   //添加一条记录
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_friend);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new FriendAdapter(friendList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initFAB(View view){
        FloatingActionButton fab = view.findViewById(R.id.myFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将对话框进行视图压缩
                View dialog = LayoutInflater.from(getContext()).inflate(R.layout.friend_dialogview,null,false);
                final EditText ed_name = dialog.findViewById(R.id.dialog_friend_name);
                final EditText ed_sex = dialog.findViewById(R.id.dialog_friend_sex);
                final EditText ed_age = dialog.findViewById(R.id.dialog_friend_age);

                //创造对话框
                new AlertDialog.Builder(getContext())
                        .setTitle("朋友信息输入")
                        .setView(dialog)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Friend friend = new Friend(ed_name.getText().toString().trim(),
                                        ed_sex.getText().toString().trim(),
                                        Integer.parseInt(ed_age.getText().toString().trim()));
                                friendList.add(friend);
                                adapter.notifyDataSetChanged();//提示适配器刷新数据集
                            }
                        }).show();
            }
        });
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.friend, container, false);
        initFriend();
        initRecyclerView(view);
        initFAB(view);
        return view;
    }

}
