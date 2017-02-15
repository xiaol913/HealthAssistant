package com.xianggao.healthassistant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xianggao.healthassistant.R;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.fragment
 * 文件名：  UserFragment
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/14 - 16:13
 * 描述：    个人中心
 */

public class UserFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,null);
        return view;
    }
}
