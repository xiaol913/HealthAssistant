package com.xianggao.healthassistant.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.utils.DataUtils;
import com.xianggao.healthassistant.view.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisteredActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_id_num;
    private EditText et_address;
    private EditText et_pass;
    private EditText et_password;
    private EditText et_phone_number;
    //性别
    private RadioButton rb_boy, rb_girl;
    //注册键
    private Button btnRegistered;
    //Dialog
    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_age = (EditText) findViewById(R.id.et_age);
        et_id_num = (EditText) findViewById(R.id.et_id_num);
        et_address = (EditText) findViewById(R.id.et_address);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_password = (EditText) findViewById(R.id.et_password);
        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
        btnRegistered = (Button) findViewById(R.id.btnRegistered);
        btnRegistered.setOnClickListener(this);
        //性别
        rb_boy = (RadioButton) findViewById(R.id.rb_boy);
        rb_girl = (RadioButton) findViewById(R.id.rb_girl);
        //初始化Dialog
        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_registering, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        //屏外点击无效
        dialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegistered:
                //获取到输入框的值
                String name = et_name.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String idnum = et_id_num.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                pass=DataUtils.getMD5(pass);
                String password = et_password.getText().toString().trim();
                password = DataUtils.getMD5(password);
                String phonenum = et_phone_number.getText().toString().trim();
                String address = et_address.getText().toString().trim();
                String sex = null;
                //判断是否为空
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(idnum) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phonenum) && !TextUtils.isEmpty(address)) {
                    //判断两次输入的密码是否一致
                    if (pass.equals(password)) {
                        dialog.show();
                        //判断性别
                        if (rb_boy.isChecked())
                            sex = "男";
                        else
                            sex = "女";
                        String url = "http://192.168.1.64/healthassistantsys/mobiles/userAction.php?act=userReg";
                        //将数据保存
                        JSONObject json = new JSONObject();
                        try {
                            json.put("user_phone_num",phonenum);
                            json.put("user_name",name);
                            json.put("user_id_card",idnum);
                            json.put("user_age",age);
                            json.put("user_sex",sex);
                            json.put("user_address",address);
                            json.put("user_password",password);
                            json.put("user_level",0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        HttpParams params = new HttpParams();
                        params.putJsonParams(json.toString());
                        RxVolley.jsonPost(url, params, new HttpCallback() {
                            @Override
                            public void onSuccess(String t) {
                                //判断是否注册成功
                                dialog.dismiss();
                                if (t.toString().equals("true")){
                                    Toast.makeText(RegisteredActivity.this, R.string.register_completed, Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(RegisteredActivity.this, R.string.register_fail, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(this, R.string.twice_different, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, R.string.txt_cannot_null, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
