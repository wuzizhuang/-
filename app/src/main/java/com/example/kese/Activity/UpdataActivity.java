package com.example.kese.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.kese.Model.ListModel;
import com.example.kese.R;
import com.example.kese.util.DataBaseHelper;
import com.example.kese.util.MyDataHelper;
import com.example.kese.util.StringUtils;

public class UpdataActivity extends AppCompatActivity {

    TextView upData_name;
    TextView upData_phoneNumber;
    Button upData_button;
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);
        Intent intent =getIntent();
        int id=intent.getIntExtra("id",0);
        String phoneNumber=intent.getStringExtra("number");
        String name=intent.getStringExtra("name");

        upData_button=findViewById(R.id.upData__button);
        upData_name=findViewById(R.id.upData_name);
        upData_phoneNumber=findViewById(R.id.upData_phone);

        upData_name.setText(name);
        upData_phoneNumber.setText(phoneNumber);

        upData_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListModel listModel=new ListModel(id,upData_phoneNumber.getText().toString(),upData_name.getText().toString());
                MyDataHelper myDataHelper=new MyDataHelper(UpdataActivity.this);
                myDataHelper.getWritableDatabase();
                String flag=myDataHelper.upDataOne(listModel);//更新
                Toast.makeText(UpdataActivity.this,flag,Toast.LENGTH_SHORT).show();
                myDataHelper.close();
                finish();//返回
            }
        });

    }


}


