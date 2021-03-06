package com.example.android.moovies.ui.common.view_pager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.List;

class CustomViewPager {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FragmentManager mFragmentManager;

    private List<Fragment> mFragmentList;
    private List<String> mStringList;
    private int currentTab = -1;

    CustomViewPager(List<Fragment> mFragmentList, List<String> mStringList, FragmentManager mFragmentManager, ViewPager mViewPager, TabLayout mTabLayout, int currentTab) {
        this.mFragmentList = mFragmentList;
        this.mStringList = mStringList;
        this.mFragmentManager = mFragmentManager;
        this.mViewPager = mViewPager;
        this.mTabLayout = mTabLayout;
        this.currentTab = currentTab;

        setupTabLayout();
        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(mFragmentManager);
        for (int i = 0; i < mFragmentList.size(); i++) {
            adapter.addFrag(mFragmentList.get(i), mStringList.get(i));
        }
        viewPager.setAdapter(adapter);
        if (currentTab > 0 && currentTab <= 3) viewPager.setCurrentItem(currentTab);
        else if (currentTab == 6)viewPager.setCurrentItem(2);
        else if (currentTab == 7)viewPager.setCurrentItem(3);
        else if (currentTab < 0) viewPager.setCurrentItem((currentTab * (-1)));
        else viewPager.setCurrentItem(currentTab%2);
    }

    private void setupTabLayout() {

        mTabLayout.setupWithViewPager(mViewPager);

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
