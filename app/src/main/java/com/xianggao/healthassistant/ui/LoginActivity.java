package com.xianggao.healthassistant.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.utils.DataUtils;
import com.xianggao.healthassistant.utils.JsonUtils;
import com.xianggao.healthassistant.utils.ShareUtils;
import com.xianggao.healthassistant.view.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_phone_num, et_password;
    private CheckBox keep_password;
    private Button btn_login, btn_registered;
    private TextView tv_forget;
    private CustomDialog dialog;
    private String url = "http://192.168.1.64/healthassistantsys/mobiles/userAction.php?act=userLogin";
    private HashMap<String, String> session= new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        et_phone_num = (EditText) findViewById(R.id.et_phone_num);
        et_password = (EditText) findViewById(R.id.et_password);
        keep_password = (CheckBox) findViewById(R.id.keep_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        btn_registered = (Button) findViewById(R.id.btn_registered);
        btn_registered.setOnClickListener(this);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);
        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loding, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        dialog.setCancelable(false);
        boolean isCheck = ShareUtils.getBoolean(this, "HA_keepPass", false);
        keep_password.setChecked(isCheck);
        if (isCheck) {
            et_phone_num.setText(ShareUtils.getString(this, "HA_phoneNum", ""));
            et_password.setText(ShareUtils.getString(this, "HA_password", ""));
        }
    }


    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String phone_num = et_phone_num.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                //判断是否为空
                if (!TextUtils.isEmpty(phone_num) && !TextUtils.isEmpty(password)) {
                    dialog.show();
                    password = DataUtils.getMD5(password);
                    JSONObject json = new JSONObject();
                    try {
                        json.put("user_phone_num", phone_num);
                        json.put("user_password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    HttpParams params = new HttpParams();
                    params.putJsonParams(json.toString());
                    RxVolley.jsonPost(url, params, new HttpCallback() {
                        @Override
                        public void onSuccess(Map<String, String> headers, byte[] t) {
                            dialog.dismiss();
                            String result = new String(t);
                            if (result.equals("not exist")) {
                                Toast.makeText(getApplication(), R.string.not_exist, Toast.LENGTH_SHORT).show();
                            } else if (result.equals("wrong")) {
                                Toast.makeText(getApplication(), R.string.pass_wrong, Toast.LENGTH_SHORT).show();
                            } else {
                                session = JsonUtils.parsingJson(session,result);
                                session.put("sessionId",headers.get("Set-Cookie"));
                                Context context = view.getContext();
                                Intent intent = new Intent(context, MainActivity.class);
                                Bundle map = new Bundle();
                                map.putSerializable("sessionId",session);
                                intent.putExtra("session",map);
                                Toast.makeText(getApplication(), R.string.login_success, Toast.LENGTH_SHORT).show();
                                context.startActivity(intent);
                                finish();
                            }

                        }
                    });
                } else {
                    Toast.makeText(this, R.string.txt_cannot_null, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_registered:
                startActivity(new Intent(this, RegisteredActivity.class));
                break;
        }
    }

    //假设我输入用户名密码，但是我不点击登录，而是直接退出了
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //保存状态
        ShareUtils.putBoolean(this, "HA_keepPass", keep_password.isChecked());
        //是否记住密码
        if (keep_password.isChecked()) {
            //记住用户名和密码
            ShareUtils.putString(this, "HA_phoneNum", et_phone_num.getText().toString().trim());
            ShareUtils.putString(this, "HA_password", et_password.getText().toString().trim());
        } else {
            ShareUtils.delShare(this, "HA_phoneNum");
            ShareUtils.delShare(this, "HA_password");
        }
    }
}
