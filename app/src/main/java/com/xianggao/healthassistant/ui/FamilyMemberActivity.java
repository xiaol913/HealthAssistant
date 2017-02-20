package com.xianggao.healthassistant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.entity.CaseData;
import com.xianggao.healthassistant.entity.UserData;
import com.xianggao.healthassistant.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FamilyMemberActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_mem_name;
    private EditText et_mem_age;
    private EditText et_mem_id_card;
    private EditText et_mem_phone;
    private EditText et_mem_address;
    private ListView lv_member;
    private RadioButton rb_mem_male, rb_mem_female;
    private Button btn_member_edit;
    private Button btn_member_del;
    private Button btn_member_save;
    private String url = "http://192.168.1.64/healthassistantsys/mobiles/userAction.php?act=getUser";
    private String delUrl = "http://192.168.1.64/healthassistantsys/mobiles/userAction.php?act=delFam";
    private String editUrl = "http://192.168.1.64/healthassistantsys/mobiles/userAction.php?act=editFam";
    private List<UserData> mUserList = new ArrayList<>();
    private List<CaseData> mCaseList = new ArrayList<>();
    private String user_id = null;
    private String user_family_num = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_member);
        initView();
        initData();
    }

    private void initData() {
        user_id = this.getIntent().getStringExtra("user_id");
        user_family_num = this.getIntent().getStringExtra("user_family_num");
        JSONObject json = new JSONObject();
        try {
            json.put("user_id", user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpParams params = new HttpParams();
        params.putJsonParams(json.toString());
        RxVolley.jsonPost(url, params, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                if (t.equals("wrong")) {
                    Toast.makeText(getApplicationContext(), "读取数据错误", Toast.LENGTH_SHORT).show();
                } else {
                    mUserList = JsonUtils.parsingCase(getApplicationContext(), lv_member, mUserList, mCaseList, t);
                    et_mem_name.setText(mUserList.get(0).getUser_name());
                    et_mem_age.setText(mUserList.get(0).getUser_age());
                    et_mem_id_card.setText(mUserList.get(0).getUser_id_card());
                    et_mem_phone.setText(mUserList.get(0).getUser_phone_num());
                    et_mem_address.setText(mUserList.get(0).getUser_address());
                    if (mUserList.get(0).getUser_sex().equals("男")) {
                        rb_mem_male.setChecked(true);
                    } else {
                        rb_mem_female.setChecked(true);
                    }
                }
            }
        });
        lv_member.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), CaseInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user_name", mCaseList.get(position).getUser_name());
                bundle.putString("user_age", mCaseList.get(position).getUser_age());
                bundle.putString("case_date", mCaseList.get(position).getCase_date());
                bundle.putString("case_desc", mCaseList.get(position).getCase_desc());
                bundle.putString("hos_name", mCaseList.get(position).getHos_name());
                bundle.putString("hos_part", mCaseList.get(position).getHos_part());
                bundle.putString("type_name", mCaseList.get(position).getType_name());
                intent.putExtra("case_info", bundle);
                if (!mCaseList.get(position).getHos_name().equals("null")) {
                    startActivity(intent);
                }
            }
        });
    }

    private void initView() {
        et_mem_name = (EditText) findViewById(R.id.et_mem_name);
        et_mem_age = (EditText) findViewById(R.id.et_mem_age);
        et_mem_id_card = (EditText) findViewById(R.id.et_mem_id_card);
        et_mem_phone = (EditText) findViewById(R.id.et_mem_phone);
        et_mem_address = (EditText) findViewById(R.id.et_mem_address);
        lv_member = (ListView) findViewById(R.id.lv_member);
        btn_member_edit = (Button) findViewById(R.id.btn_member_edit);
        btn_member_edit.setOnClickListener(this);
        btn_member_del = (Button) findViewById(R.id.btn_member_del);
        btn_member_del.setOnClickListener(this);
        btn_member_save = (Button) findViewById(R.id.btn_member_save);
        btn_member_save.setOnClickListener(this);
        rb_mem_male = (RadioButton) findViewById(R.id.rb_mem_male);
        rb_mem_female = (RadioButton) findViewById(R.id.rb_mem_female);
        setEnabled(false);
        btn_member_save.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_member_edit:
                btn_member_save.setVisibility(View.VISIBLE);
                btn_member_del.setVisibility(View.GONE);
                btn_member_edit.setVisibility(View.GONE);
                setEnabled(true);
                break;
            case R.id.btn_member_del:
                JSONObject json = new JSONObject();
                try {
                    json.put("user_id", user_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HttpParams params = new HttpParams();
                params.putJsonParams(json.toString());
                RxVolley.jsonPost(delUrl, params, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        if (t.equals("fail")) {
                            Toast.makeText(getApplicationContext(), "删除失败！请稍后再试！", Toast.LENGTH_SHORT).show();
                        } else if (t.equals("success")) {
                            Toast.makeText(getApplicationContext(), "删除成功，请重新登录！", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        }
                    }
                });
                break;
            case R.id.btn_member_save:
                String user_name = et_mem_name.getText().toString().trim();
                String user_age = et_mem_age.getText().toString().trim();
                String user_id_card = et_mem_id_card.getText().toString().trim();
                String user_phone_num = et_mem_phone.getText().toString().trim();
                String user_address = et_mem_address.getText().toString().trim();
                String user_sex = null;
                if (rb_mem_male.isChecked())
                    user_sex = "男";
                else
                    user_sex = "女";
                JSONObject object = new JSONObject();
                try {
                    object.put("user_id", user_id);
                    object.put("user_name", user_name);
                    object.put("user_age", user_age);
                    object.put("user_id_card", user_id_card);
                    object.put("user_phone_num", user_phone_num);
                    object.put("user_address", user_address);
                    object.put("user_sex", user_sex);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HttpParams params1 = new HttpParams();
                params1.putJsonParams(object.toString());
                RxVolley.jsonPost(editUrl, params1, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        if (t.equals("fail")) {
                            Toast.makeText(getApplicationContext(), "修改失败！请稍后再试！", Toast.LENGTH_SHORT).show();
                        } else if (t.equals("success")) {
                            Toast.makeText(getApplicationContext(), "修改成功，请重新登录！", Toast.LENGTH_SHORT).show();
                            btn_member_del.setVisibility(View.VISIBLE);
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

    public void setEnabled(boolean is) {
        et_mem_name.setEnabled(is);
        et_mem_age.setEnabled(is);
        et_mem_id_card.setEnabled(is);
        et_mem_phone.setEnabled(is);
        et_mem_address.setEnabled(is);
        rb_mem_male.setEnabled(is);
        rb_mem_female.setEnabled(is);
    }
}
