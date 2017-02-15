package com.xianggao.healthassistant.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.fragment.MainFragment;
import com.xianggao.healthassistant.fragment.NewsFragment;
import com.xianggao.healthassistant.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //TabLayout
    private TabLayout mTabLayout;
    //ViewPager
    private ViewPager mViewPager;
    //Title
    private List<String> mTitile;
    //Fragment
    private List<Fragment> mFragment;
    //悬浮窗
    private FloatingActionButton fab_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    //初始化
    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        //悬浮按钮
        fab_setting = (FloatingActionButton) findViewById(R.id.fab_setting);
        fab_setting.setOnClickListener(this);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());
        //mViewPager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            //返回item的个数
            @Override
            public int getCount() {
                return null!=mFragment?mFragment.size():0;
            }
            //设置标题

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitile.get(position);
            }
        });
        //绑定
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void initData() {
        mTitile = new ArrayList<>();
        mTitile.add("主页");
        mTitile.add("新闻");
        mTitile.add("个人中心");
        mFragment = new ArrayList<>();
        mFragment.add(new MainFragment());
        mFragment.add(new NewsFragment());
        mFragment.add(new UserFragment());
    }

    @Override
    public void onClick(View v) {

    }
}
