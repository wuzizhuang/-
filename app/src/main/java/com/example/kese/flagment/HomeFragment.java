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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kese.Activity.UpdataActivity;
import com.example.kese.Model.ListModel;
import com.example.kese.R;
import com.example.kese.adapt.ListViewAdapt;
import com.example.kese.util.MyDataHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView add_number,add_name;
    private Button add_button;
    private ListView view_all;
    private  MyDataHelper myDataHelper;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment PrivateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    public void onStart() {
        super.onStart();
        ViewAll();
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
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        add_number=v.findViewById(R.id.phone);
        add_button=v.findViewById(R.id.add_button);
        add_name=v.findViewById(R.id.name);
        view_all=v.findViewById(R.id.view_all);

        ViewAll();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDataHelper=new MyDataHelper(getContext());
                ListModel listModel=new ListModel(-1,add_number.getText().toString(), add_name.getText().toString());
                String s = myDataHelper.addOne_check(listModel);
                Toast.makeText(getContext(), "ADD+"+ add_number.getText().toString()+add_name.getText().toString(), Toast.LENGTH_SHORT).show();
                myDataHelper.close();
                ViewAll();
            }
    });
        view_all.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view, int i,long l)
            {
                ListModel listModel=(ListModel)adapterView.getItemAtPosition(i);

                AlertDialog.Builder dialog=new AlertDialog.Builder(getContext());
                dialog.setTitle("请选择");
                dialog.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myDataHelper=new MyDataHelper(getContext());
                        String s = myDataHelper.deleteOne(listModel);
                        Toast.makeText(getContext(), "delete+"+ s, Toast.LENGTH_SHORT).show();
                        ViewAll();
                        myDataHelper.close();
                    }
                });//删除
                dialog.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getContext(), UpdataActivity.class);
                        intent.putExtra("id", listModel.getId());
                        intent.putExtra("name", listModel.getName());
                        intent.putExtra("number", listModel.getPhoneNumber());
                        startActivity(intent);


                        ViewAll();
                    }
                });//修改名称和电话号码
                dialog.create();
                dialog.show();

                return;
            }
        });
        return  v;
    }


    private void ViewAll() {
        myDataHelper=new MyDataHelper(getContext());
        ArrayList<ListModel> listModels=myDataHelper.getAllList_byPhone();
        ListViewAdapt listViewAdapt=new ListViewAdapt(getContext(),R.layout.listview_item,listModels);
        view_all.setAdapter(listViewAdapt);
        myDataHelper.close();
    }
}
/*
        myDataHelper=new MyDataHelper(getContext());

        ArrayAdapter<ListModel> adapter=new ArrayAdapter<ListModel>(getContext(), android.R.layout.simple_list_item_1, myDataHelper.getAll());
        view_all.setAdapter(adapter);
        myDataHelper.close();
        */
//HomeFragment.this   Context