package com.example.kese.Activity;

import android.content.Intent;

import com.example.kese.R;

public class settingActivity extends BaseActivity {

    protected int id;
    protected  Class<?> cls;
    protected Intent intent;
    @Override
    protected int initLayout() {
        return R.layout.fragment_private_page1;
    }

    @Override
    protected void initView() {
        intent =getIntent();
        id = intent.getIntExtra("id",0);
        cls = (Class<?>) intent.getSerializableExtra("layout_page");
    }

    @Override
    protected void initData() {
        navigateTo(cls);
    }


}