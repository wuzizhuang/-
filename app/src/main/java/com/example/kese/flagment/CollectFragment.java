package com.example.kese.flagment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kese.R;
import com.example.kese.adapt.collectAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

public class CollectFragment extends Fragment {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
             "全部","收藏", "111"
    };
    private ViewPager viewPager;
    Button button;
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
        return v;
    }//创建碎片

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        for (String title : mTitles) {
            mFragments.add(CollectBoxFragement.newInstance(title));
        }
        viewPager.setAdapter(new collectAdapter(getFragmentManager(),mTitles,mFragments));
        slidingTabLayout.setViewPager(viewPager);
    }
}