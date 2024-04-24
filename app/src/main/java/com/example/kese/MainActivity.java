package com.example.kese;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.kese.Activity.BaseActivity;
import com.example.kese.Activity.loginActivity;
import com.example.kese.Activity.registerActivity;

public class MainActivity extends BaseActivity {

    private Button buttonLogin;
    private Button buttonRegister;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        buttonLogin=findViewById(R.id.button_login);
        buttonRegister=findViewById(R.id.button_register);
    }

    @Override
    protected void initData() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent in = new Intent(MainActivity.this, loginActivity.class);
                //startActivity(in);
                navigateTo(loginActivity.class);
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent in = new Intent(MainActivity.this, registerActivity.class);
                //startActivity(in);
                navigateTo(registerActivity.class);
            }
        });
    }
}