package com.example.kese.flagment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.kese.Activity.PrivateSettingFirstActivity;
import com.example.kese.Activity.PrivateSettingSecondActivity;
import com.example.kese.Activity.PrivateSettingThirdActivity;
import com.example.kese.Activity.settingActivity;
import com.example.kese.Model.ListModel;
import com.example.kese.R;
import com.example.kese.adapt.ListViewAdapt_private;
import com.example.kese.util.MyDataHelper;

import java.util.ArrayList;


public class PrivateFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private MyDataHelper myDataHelper_private;

    private ListView view_private;
    Button button;
    public PrivateFragment() {
            }

      public static PrivateFragment newInstance() {
        PrivateFragment fragment = new PrivateFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_private, container, false);
        view_private=view.findViewById(R.id.view_private);
        ViewAll_private();
        view_private.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void  onItemClick(AdapterView<?> adapterView,View view, int i,long l)
            {
                ListModel OIC_DataHelper = (ListModel) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(getContext(), OIC_DataHelper.getLayout_page());
                intent.putExtra("id", OIC_DataHelper.getId());
                intent.putExtra("layout_page", OIC_DataHelper.getLayout_page());
                startActivity(intent);

                ViewAll_private();
            }

        });

        /*
        view_private.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view, int i,long l)
            {
                ListModel OIC_DataHelper=(ListModel)adapterView.getItemAtPosition(i);
                AlertDialog.Builder dialog=new AlertDialog.Builder(getContext());
                dialog.setTitle("请选择");

                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getContext(), OIC_DataHelper.getLayout_page());
                        intent.putExtra("id",OIC_DataHelper.getId());
                        intent.putExtra("layout_page",OIC_DataHelper.getLayout_page());
                        startActivity(intent);


                        ViewAll_private();
                    }
                });//修改名称和电话号码
                dialog.create();
                dialog.show();

                return;
            }
        });
        * */

        return view;
    }
    private void ViewAll_private() {
        ArrayList<ListModel> listModels=getAllList_byPrivate();
        ListViewAdapt_private listViewAdapt_private=new ListViewAdapt_private(getContext(),R.layout.listview_item_my,listModels);
        view_private.setAdapter(listViewAdapt_private);
    }
    public ArrayList<ListModel> getAllList_byPrivate()
    {

        ArrayList<ListModel> arrayList=new ArrayList<ListModel>();
        String setting_1 ="设置";
        arrayList.add(new ListModel(1, setting_1 , setting_1, PrivateSettingFirstActivity.class));

        String setting_2 ="设置";
        arrayList.add(new ListModel(2, setting_1 , setting_2, PrivateSettingSecondActivity.class));

        String setting_3 ="设置";
        arrayList.add(new ListModel(3, setting_1 , setting_3, PrivateSettingThirdActivity.class));
        return arrayList;
    }
}