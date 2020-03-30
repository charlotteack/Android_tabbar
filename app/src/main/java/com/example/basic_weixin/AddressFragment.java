package com.example.basic_weixin;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment {
    private List<Person> personList = new ArrayList<>();

    public AddressFragment() {
        // Required empty public constructor
    }

    //展示的数据
    private void initPerson() {
        Person kelan1 = new Person(R.drawable.kelan, "柯南1号来了");
        Person kelan2 = new Person(R.drawable.kelan2, "柯南2号来了");
        Person kelan3 = new Person(R.drawable.kelan3, "柯南1号来了");
        Person xiaoai1 = new Person(R.drawable.xiaoai, "小哀1号来了");
        Person xiaoai2 = new Person(R.drawable.xiaoai2, "小哀2号来了");
        personList.add(kelan1);
        personList.add(kelan2);
        personList.add(kelan3);
        personList.add(xiaoai1);
        personList.add(xiaoai2);
    }

    public void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        PersonAdapter adapter = new PersonAdapter(personList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.address, container, false);
        initPerson();
        initRecyclerView(view);
        return view;
    }

}
