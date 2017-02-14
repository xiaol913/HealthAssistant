package com.xianggao.healthassistant.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.entity.UserData;
import com.xianggao.healthassistant.view.CustomDialog;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class RegisteredActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_id_num;
    //性别
    private RadioButton rb_boy, rb_girl;
    private EditText et_pass;
    private EditText et_password;
    private EditText et_phone_number;
    private EditText et_verify_code;
    private Button btnRegistered;
    private Button btn_get_verify;
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
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_password = (EditText) findViewById(R.id.et_password);
        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
        et_verify_code = (EditText) findViewById(R.id.et_verify_code);
        btnRegistered = (Button) findViewById(R.id.btnRegistered);
        btnRegistered.setOnClickListener(this);
        btn_get_verify = (Button) findViewById(R.id.btn_get_verify);
        btn_get_verify.setOnClickListener(this);
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
            case R.id.btn_get_verify:
                String phonenum0 = et_phone_number.getText().toString().trim();
                //判断手机号是否为空
                if (!TextUtils.isEmpty(phonenum0)) {
                    BmobSMS.requestSMSCode(phonenum0, "欢迎注册", new QueryListener<Integer>() {
                        @Override
                        public void done(Integer integer, BmobException e) {
                            if (e == null) {
                                et_phone_number.setEnabled(false);
                                Toast.makeText(RegisteredActivity.this, R.string.sms_sent, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisteredActivity.this, R.string.sms_send_fail, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisteredActivity.this, R.string.please_enter_phone, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRegistered:
                //获取到输入框的值
                String name = et_name.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String idnum = et_id_num.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String phonenum = et_phone_number.getText().toString().trim();
                final String verify_code = et_verify_code.getText().toString().trim();
                //判断是否为空
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(idnum) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phonenum)) {
                    //判断两次输入的密码是否一致
                    if (pass.equals(password)) {
                        dialog.show();
                        //注册
                        final UserData user = new UserData();
                        //判断性别
                        if (rb_boy.isChecked())
                            user.setSex(true);
                        else
                            user.setSex(false);
                        user.setUser_name(name);
                        user.setPassword(password);
                        user.setAge(Integer.parseInt(age));
                        user.setIdNumber(Integer.parseInt(idnum));
                        user.setMobilePhoneNumber(phonenum);
                        //判断验证码是否正确
                        BmobSMS.verifySmsCode(phonenum, verify_code, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e==null){
                                    user.signOrLogin(verify_code, new SaveListener<UserData>() {
                                        @Override
                                        public void done(UserData userData, BmobException e) {
                                            dialog.dismiss();
                                            if (e == null) {
                                                Toast.makeText(RegisteredActivity.this, R.string.register_completed, Toast.LENGTH_SHORT).show();
                                                finish();
                                            } else {
                                                Toast.makeText(RegisteredActivity.this, getString(R.string.register_fail) + e.toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }else {
                                    Toast.makeText(RegisteredActivity.this, R.string.verify_fail, Toast.LENGTH_SHORT).show();
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
