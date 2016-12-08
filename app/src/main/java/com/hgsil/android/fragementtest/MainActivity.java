package com.hgsil.android.fragementtest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout mTabLayout ;
    ViewpagerAdapter mViewpagerAdapter;
    ViewPager mViewPager;
    String[] mtitle =new String[]{"第一个Tab","第二个Tab","第三个Tab","第四个Tab","第五个Tab","第六个Tab","第七个Tab",
            "第八个Tab","第九个Tab","第十个Tab"};
    List<Fragment> mFragments ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //最外层的Fragment
        initView();


    }
    private void initView(){
        mTabLayout = (TabLayout) findViewById(R.id.Tab1);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_main);


        mViewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager());
        //设置标题
        mViewpagerAdapter.setTitles(mtitle);
        //获取Fragment集合 并传入Fragment
        mFragments =new ArrayList<>();

        //将所有的title传入Fragment_1对象 将textview内容改变
        for (int i = 0; i <mtitle.length ; i++) {
            Fragment_1 mFragment = new Fragment_1();
            mFragment.setMtitle(mtitle[i]);
            mFragments.add(mFragment);
        }
        mViewpagerAdapter.setFragments(mFragments);
        //设置TabLayout的显示模式：滚动或者静止
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //给viewPager设置适配器
        mViewPager.setAdapter(mViewpagerAdapter);
        //TabLayout绑定ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
