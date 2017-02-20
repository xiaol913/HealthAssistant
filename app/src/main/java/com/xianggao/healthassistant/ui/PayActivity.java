package com.xianggao.healthassistant.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.utils.L;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PayActivity extends BaseActivity implements View.OnClickListener {
    private String user_id;
    private String user_name;
    private String type_id;
    private String res_end;
    private String type_name;
    private TextView do_R_name, do_R_time, do_R_part, do_R_type;
    private Button btn_do_R_submit;
    private String hos_name = "省中院";
    private String hos_part = "化验科";
    private String hos_id = "1";
    private String url = "http://192.168.1.64/healthassistantsys/mobiles/userAction.php?act=postR";
    private HashMap<String, String> session = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initView();
        initData();
    }

    private void initView() {
        do_R_name = (TextView) findViewById(R.id.do_R_name);
        do_R_time = (TextView) findViewById(R.id.do_R_time);
        do_R_part = (TextView) findViewById(R.id.do_R_part);
        do_R_type = (TextView) findViewById(R.id.do_R_type);
        btn_do_R_submit = (Button) findViewById(R.id.btn_do_R_submit);
        btn_do_R_submit.setOnClickListener(this);
    }

    private void initData() {
        user_id = this.getIntent().getStringExtra("user_id");
        user_name = this.getIntent().getStringExtra("user_name");
        type_name = this.getIntent().getStringExtra("type_name");
        type_id = this.getIntent().getStringExtra("type_id");
        res_end = this.getIntent().getStringExtra("res_end");
        do_R_name.setText(user_name);
        do_R_time.setText(res_end);
        String part_name = hos_name + hos_part;
        do_R_part.setText(part_name);
        do_R_type.setText(type_name);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_do_R_submit:
                JSONObject json = new JSONObject();
                try {
                    json.put("res_user_id", user_id);
                    json.put("res_hos_id", hos_id);
                    json.put("res_end", res_end);
                    json.put("res_type", type_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HttpParams params = new HttpParams();
                params.putJsonParams(json.toString());
                RxVolley.jsonPost(url, params, new HttpCallback() {
                    @Override
                    public void onSuccess(Map<String, String> headers, byte[] t) {
                        String str = new String(t);
                        if (str.equals("false")) {
                            System.out.println(str);
                            Toast.makeText(getApplicationContext(), "预约失败！请稍后再试！", Toast.LENGTH_SHORT).show();
                        } else if (str.equals("true")) {
                            Toast.makeText(getApplication(), "预约成功！", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            L.e(str);
                        }
                    }
                });
                break;
        }
    }
}
