package com.example.kese.flagment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.kese.Activity.selectResultActivity;
import com.example.kese.R;
import com.example.kese.adapt.collectAdapter;
import com.example.kese.util.SelectHelper;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectFragment extends Fragment {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {
            //调用SelectHelper.java中的数据库
    };

    private ViewPager viewPager;
    private Button select_button;
    private SlidingTabLayout slidingTabLayout;
    public CollectFragment() {
        // Required empty public constructor
    }


    public static CollectFragment newInstance() {
        CollectFragment fragment = new CollectFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_collect,container,false);
        viewPager =v.findViewById(R.id.fixedViewPager);

        slidingTabLayout=v.findViewById(R.id.slidingTabLayout);
        select_button=v.findViewById(R.id.select_button);
        select_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent in =new Intent(getContext(), selectResultActivity.class);
                startActivity(in);
                Toast.makeText(getContext(),"已选择",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }//创建碎片

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        readSellect();
        for (String title : mTitles) {
            mFragments.add(CollectBoxFragement.newInstance(title));
        }
        viewPager.setAdapter(new collectAdapter(getFragmentManager(),mTitles,mFragments));
        slidingTabLayout.setViewPager(viewPager);
    }
    public void readSellect(){

        SelectHelper selectHelper=new SelectHelper(getContext());
        selectHelper.getReadableDatabase();

        ArrayList<String> arrayList=selectHelper.getAll_show();

        mTitles= arrayList.toArray(new String[arrayList.size()]);

    }
}