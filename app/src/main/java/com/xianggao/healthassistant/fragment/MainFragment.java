package com.xianggao.healthassistant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.adapter.MainMenuAdapter;
import com.xianggao.healthassistant.ui.MyAssayActivity;
import com.xianggao.healthassistant.ui.MyQuestionsActivity;
import com.xianggao.healthassistant.ui.MyReservationsActivity;
import com.xianggao.healthassistant.ui.QuickAskActivity;
import com.xianggao.healthassistant.utils.DataUtils;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.fragment
 * 文件名：  MainFragment
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/14 - 16:13
 * 描述：    主页
 */

public class MainFragment extends Fragment {
    private RecyclerView view_recycler;
    String[] menuName;
    int[] icons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        menuName = this.getActivity().getResources().getStringArray(R.array.main_list);
        view_recycler = (RecyclerView) getView().findViewById(R.id.view_recycler);
        view_recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        MainMenuAdapter adapter = new MainMenuAdapter(getActivity(), DataUtils.getMenuList(icons, menuName));
        view_recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new MainMenuAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(getActivity(), QuickAskActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), MyQuestionsActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), MyReservationsActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), MyAssayActivity.class));
                        break;
                    case 4:
                        Toast.makeText(getActivity(),"暂无",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}