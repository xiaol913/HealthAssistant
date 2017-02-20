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

import org.json.JSONException;
import org.json.JSONObject;

public class AddFamilyMember extends BaseActivity implements View.OnClickListener {
    private EditText et_fam_phone,et_fam_password;
    private Button btn_submit;
    private String url = "http://192.168.1.64/healthassistantsys/mobiles/userAction.php?act=addFamMem";
    private String user_family_num =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_family_member);
        initView();
    }

    private void initView() {
        et_fam_phone= (EditText) findViewById(R.id.et_fam_phone);
        et_fam_password= (EditText) findViewById(R.id.et_fam_password);
        btn_submit= (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        user_family_num = this.getIntent().getStringExtra("user_family_num");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                String phone = et_fam_phone.getText().toString().trim();
                String password = et_fam_password.getText().toString().trim();
                password = DataUtils.getMD5(password);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("user_phone_num",phone);
                    jsonObject.put("user_password",password);
                    jsonObject.put("user_family_num",user_family_num);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HttpParams params = new HttpParams();
                params.putJsonParams(jsonObject.toString());
                RxVolley.jsonPost(url, params, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        if (t.equals("not exist")){
                            Toast.makeText(getApplicationContext(),"该用户并不存在！",Toast.LENGTH_SHORT).show();
                        }else if (t.equals("wrong")){
                            Toast.makeText(getApplicationContext(),"密码错误！",Toast.LENGTH_SHORT).show();
                        }else if (t.equals("success")){
                            Toast.makeText(getApplicationContext(),"添加成功，请重新登录！",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"添加失败，请稍后再试！",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
