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
import com.xianggao.healthassistant.utils.L;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterFamilyActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_fam_name;
    private Button btn_submit;
    private String user_id = null;
    private String url = "http://192.168.1.64/healthassistantsys/mobiles/userAction.php?act=createFamily";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_family);
        et_fam_name = (EditText) findViewById(R.id.et_fam_name);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        user_id = this.getIntent().getStringExtra("user_id");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                JSONObject json = new JSONObject();
                try {
                    json.put("user_id", user_id);
                    json.put("fam_name", et_fam_name.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HttpParams params = new HttpParams();
                params.putJsonParams(json.toString());
                RxVolley.jsonPost(url, params, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        if (t.equals("exist")) {
                            Toast.makeText(getApplicationContext(), "该家庭名称已经存在!", Toast.LENGTH_SHORT).show();
                        } else if (t.equals("fail")) {
                            Toast.makeText(getApplicationContext(), "创建失败，请稍后重试!", Toast.LENGTH_SHORT).show();
                        } else if (t.equals("success")) {
                            Toast.makeText(getApplicationContext(), "创建成功！请重新登录！", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        } else {
                            L.e(t);
                        }
                    }
                });
                break;
        }
    }
}
