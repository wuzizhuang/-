package com.example.kese.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.kese.R;
import com.example.kese.util.DataBaseHelper;
import com.example.kese.util.StringUtils;

public class registerActivity extends BaseActivity {
    private EditText etRegisterAccount;
    private EditText etRegisterPassword;
    private EditText etRegisterPassword2;
    private Button buttonRegister;

    DataBaseHelper dataBaseHelper;

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        etRegisterAccount=findViewById(R.id.et_register_AccountName);
        etRegisterPassword=findViewById(R.id.et_Register_Password);
        etRegisterPassword2=findViewById(R.id.et_Register_Password2);
        buttonRegister=findViewById(R.id.button_register);

    }

    @Override
    protected void initData() {
        dataBaseHelper = new DataBaseHelper(this);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String account =etRegisterAccount.getText().toString().trim();
                String pwd =etRegisterPassword.getText().toString().trim();
                String pwd2 =etRegisterPassword2.getText().toString().trim();
                register(account,pwd,pwd2);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void register(String account,String pwd,String pwd2) {
        if (StringUtils.isEmpty(account)) {
            showToast("请输入账号");
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }
        if (StringUtils.isEmpty(pwd2)) {
            showToast("请输入密码");
            return;
        }
        else{
            if(pwd.equals(pwd2)){
                Boolean checkName= dataBaseHelper.checkUsername(account);
                if(checkName==false){
                    Boolean checkPwd=dataBaseHelper.insertData(account,pwd);
                    if(checkPwd==true){
                        showToast("注册成功");
                        navigateTo(loginActivity.class);
                    }
                    else{
                        showToast("注册失败");
                    }
                }else {
                    showToast("用户名已存在");
                }
            }else {
                showToast("两次密码不一致");
            }
        }
    }

}