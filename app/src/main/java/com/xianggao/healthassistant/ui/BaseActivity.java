package com.xianggao.healthassistant.ui;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.ui
 * 文件名：  BaseActivity
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/14 - 0:36
 * 描述：    Activity基类
 */

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * 主要做的事情：
 * 1.统一的属性
 * 2.统一的接口
 * 3.统一的方法
 */

public class BaseActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //菜单栏操作

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
