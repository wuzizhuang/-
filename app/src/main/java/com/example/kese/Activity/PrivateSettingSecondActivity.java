package com.example.kese.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kese.Model.ListModel;
import com.example.kese.R;
import com.example.kese.adapt.ListViewAdapt_string;
import com.example.kese.util.MyDataHelper;

import com.example.kese.Activity.UpdataActivity;
import com.example.kese.Model.ListModel;
import com.example.kese.R;
import com.example.kese.adapt.ListViewAdapt;
import com.example.kese.util.MyDataHelper;
import com.example.kese.util.SelectHelper;

import java.util.ArrayList;
import java.util.List;

public class PrivateSettingSecondActivity extends BaseActivity {
    private SelectHelper selectHelper;
    private ListView view_all;
    private Button button_collect_add;
    private TextView collect_name;

      @Override
    protected int initLayout() {
        return R.layout.fragment_private_page2;
    }

    @Override
    protected void initView() {
          view_all = findViewById(R.id.view_select);
          button_collect_add = findViewById(R.id.add_collect_button);
          collect_name = findViewById(R.id.add_collect_name);
    }

    @Override
    protected void initData() {
        ViewAll();
        button_collect_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectHelper=new SelectHelper(PrivateSettingSecondActivity.this);
                selectHelper.getWritableDatabase();
                String flag =selectHelper.addOne(collect_name.getText().toString());
                selectHelper.close();
                showToast(flag);
                ViewAll();
            }
        });
        view_all.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListModel listModel = (ListModel) adapterView.getItemAtPosition(i);
                AlertDialog.Builder dialog = new AlertDialog.Builder(PrivateSettingSecondActivity.this);
                dialog.setTitle("删除确认");
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectHelper = new SelectHelper(PrivateSettingSecondActivity.this);
                        selectHelper.getReadableDatabase();
                        String flag = selectHelper.deleteOne(listModel.getName());
                        selectHelper.close();
                        showToast(flag);
                        ViewAll();
                    }
                });
                dialog.create();
                dialog.show();

                return;
            }
        });

    }
    private void ViewAll() {
        selectHelper = new SelectHelper(this);
        selectHelper.getReadableDatabase();
        ArrayList<ListModel> s=selectHelper.getAll_listModel();
        ListViewAdapt_string listViewAdapt_string=new ListViewAdapt_string(this,R.layout.listview_item_select,s);
        view_all.setAdapter(listViewAdapt_string);
        selectHelper.close();
    }
}

/*
private ListView v = findViewById(R.id.view_select);
    private ListView v = null;
    private SelectHelper selectHelper;
    @Override
    protected int initLayout() {
        return R.layout.fragment_private_page2;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListModel listModel = (ListModel) v.getItemAtPosition(i);
                AlertDialog.Builder dialog = new AlertDialog.Builder(PrivateSettingSecondActivity.this);
                dialog.setTitle("删除确认");
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });//取消操作
                dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        selectHelper.deleteOne(listModel.getName());
                        //ViewAll();
                        dialog.dismiss();
                        selectHelper.close();
                    }
                });
                dialog.create();
                dialog.show();

                return;
            }

        });
    }
    private void ViewAll() {
        selectHelper = new SelectHelper(this);

        ArrayList<ListModel> s=selectHelper.getAll_listModel();
        ListViewAdapt_string listViewAdapt=new ListViewAdapt_string(this,R.layout.listview_item,s);
        v.setAdapter(listViewAdapt);
        selectHelper.close();
    }



*
* */
