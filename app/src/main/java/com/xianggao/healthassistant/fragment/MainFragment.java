package com.xianggao.healthassistant.fragment;

import android.content.Context;
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
import com.xianggao.healthassistant.ui.AskQuestionActivity;
import com.xianggao.healthassistant.ui.DoReservationsActivity;
import com.xianggao.healthassistant.ui.MyQuestionsActivity;
import com.xianggao.healthassistant.ui.MyReservationsActivity;
import com.xianggao.healthassistant.ui.SelectFamilyActivity;
import com.xianggao.healthassistant.utils.DataUtils;

import java.util.HashMap;

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
    private HashMap<String, String> session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        session = (HashMap<String, String>) getActivity()
                .getIntent()
                .getBundleExtra("session")
                .getSerializable("sessionId");
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
                switch (position) {
                    case 0:
                        Context context0 = view.getContext();
                        Intent intent0 = new Intent(context0, AskQuestionActivity.class);
                        Bundle map0 = new Bundle();
                        map0.putSerializable("sessionId", session);
                        intent0.putExtra("session", map0);
                        startActivity(intent0);
                        break;
                    case 1:
                        Context context1 = view.getContext();
                        Intent intent1 = new Intent(context1, MyQuestionsActivity.class);
                        Bundle map1 = new Bundle();
                        map1.putSerializable("sessionId", session);
                        intent1.putExtra("session", map1);
                        startActivity(intent1);
                        break;
                    case 2:
                        if (session.get("user_family_num").equals("null")) {
                            Context context2 = view.getContext();
                            Intent intent2 = new Intent(context2, DoReservationsActivity.class);
                            intent2.putExtra("user_id_card", session.get("user_id_card"));
                            intent2.putExtra("user_name", session.get("user_name"));
                            startActivity(intent2);
                        } else {
                            Context context4 = view.getContext();
                            Intent intent4 = new Intent(context4, SelectFamilyActivity.class);
                            Bundle map4 = new Bundle();
                            map4.putSerializable("sessionId", session);
                            intent4.putExtra("session", map4);
                            startActivity(intent4);
                        }
                        break;
                    case 3:
                        Context context3 = view.getContext();
                        Intent intent3 = new Intent(context3, MyReservationsActivity.class);
                        Bundle map3 = new Bundle();
                        map3.putSerializable("sessionId", session);
                        intent3.putExtra("session", map3);
                        startActivity(intent3);
                        break;
                    case 4:
                        Toast.makeText(getActivity(), "暂无", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}