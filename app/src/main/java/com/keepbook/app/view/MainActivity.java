package com.keepbook.app.view;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.keepbook.app.R;
import com.keepbook.app.view.base.BaseActivity;
import com.keepbook.app.view.fragment.BIllFragment;
import com.keepbook.app.view.fragment.HomeFragment;
import com.keepbook.app.view.fragment.record.RecordFragment;
import com.keepbook.app.view.listener.PageSelectListener;
import com.keepbook.app.wdiget.MyViewPager;
import com.xuexiang.xui.adapter.FragmentAdapter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private MyViewPager mainViewpager;
    private ArrayList<Fragment> fragments;
    private FragmentAdapter<Fragment> fragmentAdapter;
    private BottomNavigationView bottomNavigation;
    private RecordFragment recordFragment;

    @Override
    protected void init() {
        initFragments();
        fragmentAdapter = new FragmentAdapter<>(getSupportFragmentManager(), fragments);
        mainViewpager.setAdapter(fragmentAdapter);
        bottomNavigation.setOnItemSelectedListener(new PageSelectListener(mainViewpager));

    }


    private void initFragments() {
        fragments = new ArrayList<>();
        /**
         * 顺序更改要和 com.keepbook.app.view.listener.PageSelectListener 该类同步更改
         */
        fragments.add(new HomeFragment());
        recordFragment = new RecordFragment();
        fragments.add(recordFragment);
        fragments.add(new BIllFragment());

    }
    public void setCategory(String category) {
        recordFragment.setCategory(category);
    }
    @Override
    protected void initView() {
        mainViewpager = (MyViewPager) findViewById(R.id.main_viewpager);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
    }
    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }
}