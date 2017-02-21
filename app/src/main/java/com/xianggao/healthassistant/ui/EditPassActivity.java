package com.xianggao.healthassistant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.utils.DataUtils;
import com.xianggao.healthassistant.utils.StaticClass;

import org.json.JSONException;
import org.json.JSONObject;

public class EditPassActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_edit;
    private EditText et_old_pass, et_new_pass, et_new_pass_again;
    private String user_id;
    private String passUrl = StaticClass.HTTP+"editPass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass);
        initView();
        initData();
    }

    private void initData() {
        user_id = this.getIntent().getStringExtra("user_id");
    }

    private void initView() {
        et_old_pass = (EditText) findViewById(R.id.et_old_pass);
        et_new_pass = (EditText) findViewById(R.id.et_new_pass);
        et_new_pass_again = (EditText) findViewById(R.id.et_new_pass_again);
        btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit:
                String oldPass = et_old_pass.getText().toString().trim();
                String newPass = et_new_pass.getText().toString().trim();
                String secPass = et_new_pass_again.getText().toString().trim();
                if (!oldPass.isEmpty() && !newPass.isEmpty() && !secPass.isEmpty()) {
                    if (newPass.equals(secPass)) {
                        oldPass = DataUtils.getMD5(oldPass);
                        newPass = DataUtils.getMD5(newPass);
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("user_id", user_id);
                            jsonObject.put("oldPass", oldPass);
                            jsonObject.put("newPass", newPass);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        HttpParams params = new HttpParams();
                        params.putJsonParams(jsonObject.toString());
                        RxVolley.jsonPost(passUrl, params, new HttpCallback() {
                            @Override
                            public void onSuccess(String t) {
                                if (t.equals("fail")) {
                                    Toast.makeText(getApplicationContext(), "修改失败！请稍后再试！", Toast.LENGTH_SHORT).show();
                                } else if (t.equals("success")) {
                                    Toast.makeText(getApplicationContext(), "修改成功，请重新登录！", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                    finish();
                                } else if (t.equals("wrong")) {
                                    Toast.makeText(getApplicationContext(), "原密码错误！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(this, "两次密码不一致！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "输入框不能为空！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
