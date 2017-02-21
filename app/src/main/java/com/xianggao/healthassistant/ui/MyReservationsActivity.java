package com.xianggao.healthassistant.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.entity.ReservationData;
import com.xianggao.healthassistant.utils.JsonUtils;
import com.xianggao.healthassistant.utils.StaticClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyReservationsActivity extends BaseActivity {
    private ListView list_assay;
    private AlertDialog dialog;
    private List<ReservationData> mList = new ArrayList<>();

    private HashMap<String, String> session;
    private String userIdUrl = StaticClass.HTTP+"getResByUserId";
    private String famNumUrl = StaticClass.HTTP+"getResByFamNum";
    private String user_family_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservation);
        initView();
        initDialog();
        initData();
    }

    private void initData() {
        session = (HashMap<String, String>) this
                .getIntent()
                .getBundleExtra("session")
                .getSerializable("sessionId");
        user_family_num = session.get("user_family_num");

        if (user_family_num.equals("null")) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("user_id", session.get("user_id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            HttpParams params = new HttpParams();
            params.putJsonParams(jsonObject.toString());
            RxVolley.jsonPost(userIdUrl, params, new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    if (t.equals("No data")) {
                        if (dialog != null && !dialog.isShowing()) {
                            dialog.show();
                        }
                    } else {
                        JsonUtils.parsingReservation(getApplicationContext(), list_assay, mList, t);
                    }
                }
            });
        } else {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("user_family_num", user_family_num);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            HttpParams params = new HttpParams();
            params.putJsonParams(jsonObject.toString());
            RxVolley.jsonPost(famNumUrl, params, new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    if (t.equals("No data")) {
                        if (dialog != null && !dialog.isShowing()) {
                            dialog.show();
                        }
                    } else {
                        JsonUtils.parsingReservation(getApplicationContext(), list_assay, mList, t);
                    }
                }
            });
        }
    }

    private void initView() {
        list_assay = (ListView) findViewById(R.id.list_assay);
    }


    public void initDialog() {
        //构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //标题
        builder.setTitle("提示");
        //图标
        builder.setIcon(android.R.drawable.btn_dialog);
        //内容
        builder.setMessage("无任何预约！");
        builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
            //int which点击的按钮的提示符
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        //构建dialog对象
        dialog = builder.create();
    }
}
