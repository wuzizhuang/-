package com.example.kese.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.kese.R;
import com.example.kese.util.DataBaseHelper;
import com.example.kese.util.StringUtils;

public class loginActivity extends BaseActivity {
    private EditText etAccount;
    private EditText etPassword;
    private ImageView mDelete;
    private Button buttonLogin;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        etAccount=findViewById(R.id.et_AccountName);
        etPassword=findViewById(R.id.et_Password);
        buttonLogin=findViewById(R.id.button_login);
        mDelete = (ImageView) findViewById(R.id.delete);
    }

    @Override
    protected void initData() {
        dataBaseHelper=new DataBaseHelper(this);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account =etAccount.getText().toString().trim();
                String pwd =etPassword.getText().toString().trim();
                login(account,pwd);

            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void login(String account,String pwd){
        if(StringUtils.isEmpty(account)){
           showToast("请输入账号");
           return;
        }
        if((StringUtils.isEmpty(pwd))){
            showToast("请输入密码");
            return;
        }
        Boolean checkCredit = dataBaseHelper.checkUsernamePassword(account,pwd);

        if(checkCredit){
            showToast("登录成功");
            navigateTo(HomeActivity.class);

        }else{
            showToast("账号或密码错误");
        }
    }
}