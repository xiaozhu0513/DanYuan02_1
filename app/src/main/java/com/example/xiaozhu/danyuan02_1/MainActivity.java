package com.example.xiaozhu.danyuan02_1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.xiaozhu.danyuan02_1.adapters.MainFvAdapter;
import com.example.xiaozhu.danyuan02_1.fragments.CollectFragment;
import com.example.xiaozhu.danyuan02_1.fragments.MeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private MainFvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTitle();
        initView();
    }

    private void initTitle() {
        titles = new ArrayList<>();
        titles.add("我的");
        titles.add("搜藏");
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        fragments = new ArrayList<>();
        fragments.add(MeFragment.newInstance());
        fragments.add(CollectFragment.newInstance());
        adapter = new MainFvAdapter(getSupportFragmentManager(),fragments,titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position==1){
                    CollectFragment fragment = (CollectFragment) fragments.get(position);
                    fragment.query();
                }
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
