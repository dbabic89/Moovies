package com.example.android.moovies.ui.common.view_pager;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import java.util.List;

public class CustomViewPager {

    List<Fragment> mFragmentList;
    List<String> mStringList;
    FragmentManager mFragmentManager;
    ViewPager mViewPager;
    TabLayout mTabLayout;

    public CustomViewPager(List<Fragment> mFragmentList, List<String> mStringList, FragmentManager mFragmentManager, ViewPager mViewPager, TabLayout mTabLayout) {
        this.mFragmentList = mFragmentList;
        this.mStringList = mStringList;
        this.mFragmentManager = mFragmentManager;
        this.mViewPager = mViewPager;
        this.mTabLayout = mTabLayout;

        setupTabLayout();
        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(mFragmentManager);
        for (int i = 0; i < mFragmentList.size(); i++) {
            adapter.addFrag(mFragmentList.get(i), mStringList.get(i));
        }
        viewPager.setAdapter(adapter);
    }

    private void setupTabLayout() {

        mTabLayout.setupWithViewPager(mViewPager);
        LinearLayout linearLayout = (LinearLayout) mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.YELLOW);
        drawable.setSize(2, 2);
        linearLayout.setDividerPadding(30);
        linearLayout.setDividerDrawable(drawable);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
