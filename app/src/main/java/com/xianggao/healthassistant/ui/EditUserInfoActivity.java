package com.xianggao.healthassistant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.utils.StaticClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EditUserInfoActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_mem_name;
    private EditText et_mem_age;
    private EditText et_mem_id_card;
    private EditText et_mem_phone;
    private EditText et_mem_address;
    private RadioButton rb_mem_male, rb_mem_female;
    private Button btn_member_edit;
    private Button btn_member_save;
    private String editUrl = StaticClass.HTTP+"editFam";
    private String user_id = null;
    private HashMap<String, String> session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);
        initView();
        initData();
    }

    private void initData() {
        session = (HashMap<String, String>) this
                .getIntent()
                .getBundleExtra("session")
                .getSerializable("sessionId");
        et_mem_name.setText(session.get("user_name"));
        et_mem_age.setText(session.get("user_age"));
        et_mem_id_card.setText(session.get("user_id_card"));
        et_mem_phone.setText(session.get("user_phone_num"));
        et_mem_address.setText(session.get("user_address"));
        if (session.get("user_sex").equals("男")) {
            rb_mem_male.setChecked(true);
        } else {
            rb_mem_female.setChecked(true);
        }
    }


    private void initView() {
        et_mem_name = (EditText) findViewById(R.id.et_mem_name);
        et_mem_age = (EditText) findViewById(R.id.et_mem_age);
        et_mem_id_card = (EditText) findViewById(R.id.et_mem_id_card);
        et_mem_phone = (EditText) findViewById(R.id.et_mem_phone);
        et_mem_address = (EditText) findViewById(R.id.et_mem_address);
        btn_member_edit = (Button) findViewById(R.id.btn_member_edit);
        btn_member_edit.setOnClickListener(this);
        btn_member_save = (Button) findViewById(R.id.btn_member_save);
        btn_member_save.setOnClickListener(this);
        rb_mem_male = (RadioButton) findViewById(R.id.rb_mem_male);
        rb_mem_female = (RadioButton) findViewById(R.id.rb_mem_female);
        setEnabled(false);
        btn_member_save.setVisibility(View.GONE);
    }

    public void setEnabled(boolean is) {
        et_mem_name.setEnabled(is);
        et_mem_age.setEnabled(is);
        et_mem_id_card.setEnabled(is);
        et_mem_phone.setEnabled(is);
        et_mem_address.setEnabled(is);
        rb_mem_male.setEnabled(is);
        rb_mem_female.setEnabled(is);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_member_edit:
                setEnabled(true);
                btn_member_edit.setVisibility(View.GONE);
                btn_member_save.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_member_save:
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("user_id", session.get("user_id"));
                    jsonObject.put("user_name", et_mem_name.getText().toString().trim());
                    jsonObject.put("user_age", et_mem_age.getText().toString().trim());
                    jsonObject.put("user_id_card", et_mem_id_card.getText().toString().trim());
                    jsonObject.put("user_phone_num", et_mem_phone.getText().toString().trim());
                    jsonObject.put("user_address", btn_member_edit.getText().toString().trim());
                    if (rb_mem_male.isChecked()) {
                        jsonObject.put("user_sex", "男");
                    } else {
                        jsonObject.put("user_sex", "女");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HttpParams params = new HttpParams();
                params.putJsonParams(jsonObject.toString());
                RxVolley.jsonPost(editUrl, params, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        if (t.equals("fail")) {
                            Toast.makeText(getApplicationContext(), "修改失败！请稍后再试！", Toast.LENGTH_SHORT).show();
                        } else if (t.equals("success")) {
                            Toast.makeText(getApplicationContext(), "修改成功，请重新登录！", Toast.LENGTH_SHORT).show();
                            btn_member_edit.setVisibility(View.VISIBLE);
                            btn_member_save.setVisibility(View.GONE);
                            setEnabled(false);
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        } else {
                            System.out.println(t);
                        }
                    }
                });
                break;
        }
    }
}