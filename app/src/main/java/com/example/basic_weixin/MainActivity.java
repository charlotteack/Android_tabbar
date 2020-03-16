package com.example.basic_weixin;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private Fragment msg_fragment = new MessageFragment();
    private Fragment frd_fragment = new FriendFragment();
    private Fragment addr_fragment = new AddressFragment();
    private Fragment set_fragment = new SettingsFragment();
    private FragmentManager fm;

    private LinearLayout msg_layout;
    private LinearLayout frd_layout;
    private LinearLayout addr_layout;
    private LinearLayout set_layout;

    private ImageButton msg_image;
    private ImageButton frd_image;
    private ImageButton addr_image;
    private ImageButton set_image;

    private TextView msg_txt;
    private TextView frd_txt;
    private TextView addr_txt;
    private TextView set_txt;

    //添加可切换的碎片到帧布局上面
    private void initFragment() {
        fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.content, msg_fragment);
        transaction.add(R.id.content, frd_fragment);
        transaction.add(R.id.content, addr_fragment);
        transaction.add(R.id.content, set_fragment);
        transaction.commit();
    }

    //获取布局和控件
    private void initView() {
        msg_layout = findViewById(R.id.msg);
        frd_layout = findViewById(R.id.frd);
        addr_layout = findViewById(R.id.addr);
        set_layout = findViewById(R.id.set);

        msg_image = findViewById(R.id.msg_imageButton);
        frd_image = findViewById(R.id.frd_imageButton);
        addr_image = findViewById(R.id.addr_imageButton);
        set_image = findViewById(R.id.set_imageButton);

        msg_txt = findViewById(R.id.msg_text);
        frd_txt = findViewById(R.id.frd_text);
        addr_txt = findViewById(R.id.addr_text);
        set_txt = findViewById(R.id.set_text);
    }

    //隐藏所有碎片
    private void hideFragment(FragmentTransaction transaction) {
        transaction.hide(msg_fragment);
        transaction.hide(frd_fragment);
        transaction.hide(addr_fragment);
        transaction.hide(set_fragment);
    }

    //切换碎片
    private void selectFragment(int i) {
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                transaction.show(msg_fragment);
                msg_image.setImageResource(R.drawable.tab_weixin_pressed);
                msg_txt.setTextColor(Color.parseColor("#00ff00"));
                break;
            case 1:
                transaction.show(frd_fragment);
                frd_image.setImageResource(R.drawable.tab_find_frd_pressed);
                frd_txt.setTextColor(Color.parseColor("#00FF00"));
                break;
            case 2:
                transaction.show(addr_fragment);
                addr_image.setImageResource(R.drawable.tab_address_pressed);
                addr_txt.setTextColor(Color.parseColor("#00FF00"));
                break;
            case 3:
                transaction.show(set_fragment);
                set_image.setImageResource(R.drawable.tab_settings_pressed);
                set_txt.setTextColor(Color.parseColor("#00FF00"));
                break;
            default:
                break;
        }
        transaction.commit();
    }

    //所有图片变灰色,文字变成白色
    private void resetImage() {
        msg_image.setImageResource(R.drawable.tab_weixin_normal);
        frd_image.setImageResource(R.drawable.tab_find_frd_normal);
        addr_image.setImageResource(R.drawable.tab_address_normal);
        set_image.setImageResource(R.drawable.tab_settings_normal);

        msg_txt.setTextColor(Color.parseColor("#ffffff"));
        frd_txt.setTextColor(Color.parseColor("#ffffff"));
        addr_txt.setTextColor(Color.parseColor("#ffffff"));
        set_txt.setTextColor(Color.parseColor("#ffffff"));
    }

    //点击事件
    @Override
    public void onClick(View view) {
        resetImage();
        switch (view.getId()) {
            case R.id.msg:
                selectFragment(0);
                break;
            case R.id.frd:
                selectFragment(1);
                break;
            case R.id.addr:
                selectFragment(2);
                break;
            case R.id.set:
                selectFragment(3);
                break;
            default:
                break;
        }
    }

    //只启动tabbar的事件监听
    private void initEvent() {
        msg_layout.setOnClickListener(this);
        frd_layout.setOnClickListener(this);
        addr_layout.setOnClickListener(this);
        set_layout.setOnClickListener(this);
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initFragment();
        initEvent();
        selectFragment(0);
    }
}
