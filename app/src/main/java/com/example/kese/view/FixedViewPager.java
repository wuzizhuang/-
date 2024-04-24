package com.example.kese.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class FixedViewPager extends ViewPager {
    public FixedViewPager(@NonNull Context context) {
        super(context);
    }

    public FixedViewPager(@NonNull Context context, @NonNull AttributeSet attr){
        super(context,attr);
    }

    @Override
    public void setCurrentItem (int item){
        super.setCurrentItem(item,false);
    }
}
