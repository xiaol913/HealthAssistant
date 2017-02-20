package com.xianggao.healthassistant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.entity.FamilyData;
import com.xianggao.healthassistant.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectFamilyActivity extends BaseActivity {
    private ListView list_member;
    private String url = "http://192.168.1.64/healthassistantsys/mobiles/userAction.php?act=getFamily";
    private List<FamilyData> mList = new ArrayList<>();
    private String user_family_num = null;
    private HashMap<String, String> session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_family);

        initView();
        initDate();
    }

    private void initDate() {
        session = (HashMap<String, String>) this
                .getIntent()
                .getBundleExtra("session")
                .getSerializable("sessionId");
        user_family_num = session.get("user_family_num");
        JSONObject json = new JSONObject();
        try {
            json.put("user_family_num", user_family_num);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpParams params = new HttpParams();
        params.putJsonParams(json.toString());
        RxVolley.jsonPost(url, params, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                mList = JsonUtils.parsingFamily(getApplicationContext(), list_member, mList, t);
            }
        });
        list_member.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DoReservationsActivity.class);
                intent.putExtra("user_id", mList.get(position).getUser_id());
                intent.putExtra("user_name", mList.get(position).getUser_name());
                startActivity(intent);
            }
        });
    }


    private void initView() {
        list_member = (ListView) findViewById(R.id.list_member);
    }
}
