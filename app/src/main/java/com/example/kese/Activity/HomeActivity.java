package com.example.kese.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.Fragment;

import com.example.kese.R;
import com.example.kese.adapt.MyPagerAdapter;
import com.example.kese.entity.TabEntity;
import com.example.kese.flagment.CollectFragment;
import com.example.kese.flagment.HomeFragment;
import com.example.kese.flagment.PrivateFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    private String[] mTitles = {"首页", "收藏", "我的"};

    
    private int[] mIconUnselectIds = {
            R.mipmap.home_unselect, R.mipmap.collect_unselect,
            R.mipmap.settings_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.home_select, R.mipmap.collect_select,
            R.mipmap.settings_select};
    private  ArrayList<Fragment> mFragments =new ArrayList<>();
    private  ArrayList<CustomTabEntity> mTabEntities =new ArrayList<>();
    private  ViewPager viewPager;
    private CommonTabLayout commonTabLayout;


    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewPager);
        commonTabLayout = findViewById(R.id.commonTabLayout);
    }

    @Override
    protected void initData() {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(CollectFragment.newInstance());
        mFragments.add(PrivateFragment.newInstance());

        for(int i =0;i<mTitles.length;i++){
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));

        }
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),mTitles,mFragments));
    }


}
/*
 EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        */