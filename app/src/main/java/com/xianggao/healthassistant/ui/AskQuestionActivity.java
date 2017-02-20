package com.xianggao.healthassistant.ui;

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
import com.xianggao.healthassistant.utils.L;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AskQuestionActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_Q_name, et_Q_age, et_Q_content, et_Q_title;
    private RadioButton rb_Q_m, rb_Q_f;
    private Button btn_Q_submit;
    private HashMap<String, String> session;
    private String postUrl = "http://192.168.1.64/healthassistantsys/mobiles/userAction.php?act=postQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        initView();
    }

    private void initView() {
        et_Q_name = (EditText) findViewById(R.id.et_Q_name);
        et_Q_age = (EditText) findViewById(R.id.et_Q_age);
        et_Q_content = (EditText) findViewById(R.id.et_Q_content);
        et_Q_title = (EditText) findViewById(R.id.et_Q_title);
        rb_Q_m = (RadioButton) findViewById(R.id.rb_Q_m);
        rb_Q_f = (RadioButton) findViewById(R.id.rb_Q_f);
        rb_Q_f.setChecked(true);
        btn_Q_submit = (Button) findViewById(R.id.btn_Q_submit);
        btn_Q_submit.setOnClickListener(this);
        session = (HashMap<String, String>) this
                .getIntent()
                .getBundleExtra("session")
                .getSerializable("sessionId");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Q_submit:
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("ask_user_id", session.get("user_id"));
                    jsonObject.put("ask_name", et_Q_name.getText().toString().trim());
                    jsonObject.put("ask_age", et_Q_age.getText().toString().trim());
                    jsonObject.put("ask_desc", et_Q_content.getText().toString().trim());
                    jsonObject.put("ask_title", et_Q_title.getText().toString().trim());
                    if (rb_Q_m.isChecked()) {
                        jsonObject.put("ask_sex", "男");
                    } else {
                        jsonObject.put("ask_sex", "女");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HttpParams params = new HttpParams();
                params.putJsonParams(jsonObject.toString());
                RxVolley.jsonPost(postUrl, params, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        if (t.equals("true")) {
                            Toast.makeText(getApplicationContext(), "提交成功！", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            L.e(t);
                            Toast.makeText(getApplicationContext(), "提交失败，请稍后再次尝试！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
