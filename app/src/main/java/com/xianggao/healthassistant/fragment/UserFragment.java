package com.xianggao.healthassistant.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.ui.EditPassActivity;
import com.xianggao.healthassistant.ui.EditUserInfoActivity;
import com.xianggao.healthassistant.ui.FamilyActivity;
import com.xianggao.healthassistant.ui.LoginActivity;

import java.util.HashMap;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.fragment
 * 文件名：  UserFragment
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/14 - 16:13
 * 描述：    个人中心
 */

public class UserFragment extends Fragment implements View.OnClickListener {
    private TextView et_name, et_age, et_id_num, et_phone_num, et_address, et_sex;
    private TextView et_quit, et_fam, et_edit, et_password;
    private HashMap<String, String> session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        initView(view);
        initData();
        return view;
    }

    private void initView(View v) {
        et_name = (TextView) v.findViewById(R.id.et_name);
        et_age = (TextView) v.findViewById(R.id.et_age);
        et_id_num = (TextView) v.findViewById(R.id.et_id_num);
        et_phone_num = (TextView) v.findViewById(R.id.et_phone_num);
        et_address = (TextView) v.findViewById(R.id.et_address);
        et_sex = (TextView) v.findViewById(R.id.et_sex);
        et_quit = (TextView) v.findViewById(R.id.et_quit);
        et_quit.setOnClickListener(this);
        et_fam = (TextView) v.findViewById(R.id.et_fam);
        et_fam.setOnClickListener(this);
        et_edit = (TextView) v.findViewById(R.id.et_edit);
        et_edit.setOnClickListener(this);
        et_password = (TextView) v.findViewById(R.id.et_password);
        et_password.setOnClickListener(this);
    }

    private void initData() {
        session = (HashMap<String, String>) getActivity()
                .getIntent()
                .getBundleExtra("session")
                .getSerializable("sessionId");
        et_name.setText(session.get("user_name"));
        et_age.setText(session.get("user_age"));
        et_id_num.setText(session.get("user_id_card"));
        et_phone_num.setText(session.get("user_phone_num"));
        et_address.setText(session.get("user_address"));
        et_sex.setText(session.get("user_sex"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_quit:
                session = null;
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.et_fam:
                Context context = v.getContext();
                Intent intent = new Intent(context, FamilyActivity.class);
                Bundle map = new Bundle();
                map.putString("user_family_num", session.get("user_family_num"));
                map.putString("user_id", session.get("user_id"));
                intent.putExtra("user_family_num", map);
                context.startActivity(intent);
                break;
            case R.id.et_edit:
                Context contextEdit = v.getContext();
                Intent intentEdit = new Intent(contextEdit, EditUserInfoActivity.class);
                Bundle mapEdit = new Bundle();
                mapEdit.putSerializable("sessionId", session);
                intentEdit.putExtra("session", mapEdit);
                contextEdit.startActivity(intentEdit);
                break;
            case R.id.et_password:
                Context contextPass = v.getContext();
                Intent intentPass = new Intent(contextPass, EditPassActivity.class);
                intentPass.putExtra("user_id", session.get("user_id"));
                contextPass.startActivity(intentPass);
                break;
        }
    }
}
