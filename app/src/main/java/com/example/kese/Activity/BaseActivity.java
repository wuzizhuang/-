package com.example.kese.Activity;

import  android.content.Context;
import android.content.Intent;
import  android.os.Bundle;
import android.widget.Toast;

import  androidx.annotation.Nullable;
import  androidx.appcompat.app.AppCompatActivity;
public abstract class  BaseActivity extends AppCompatActivity {
    public  Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        mContext = this;
        setContentView(initLayout());
        initView();
        initData();
    }

    protected abstract  int initLayout();
    protected abstract  void initView();
    protected abstract  void initData();


    public void showToast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
    public void navigateTo(Class cls){
        Intent in =new Intent(mContext,cls);
        startActivity(in);
    }
}
