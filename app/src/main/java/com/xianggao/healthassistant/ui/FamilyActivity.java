package com.xianggao.healthassistant.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.entity.FamilyData;
import com.xianggao.healthassistant.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FamilyActivity extends BaseActivity implements View.OnClickListener {
    private TextView et_fam_name;
    private ListView list_member;
    private Button btn_add;
    private String url = "http://192.168.1.64/healthassistantsys/mobiles/userAction.php?act=getFamily";
    private AlertDialog dialog;
    private List<FamilyData> mList = new ArrayList<>();
    private String user_id = null;
    private String user_family_num = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        initView();
        initDialog();
        initDate();
    }

    private void initDate() {
        user_family_num = this.getIntent().getBundleExtra("user_family_num").getString("user_family_num");
        user_id = this.getIntent().getBundleExtra("user_family_num").getString("user_id");
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
                if (t.equals("No data")) {
                    if (dialog != null && !dialog.isShowing()) {
                        dialog.show();
                    }
                } else {
                    mList = JsonUtils.parsingFamily(getApplicationContext(), list_member, mList, t);
                    et_fam_name.setText(mList.get(0).getFam_name());
                }
            }
        });
        list_member.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FamilyMemberActivity.class);
                intent.putExtra("user_id", mList.get(position).getUser_id());
                intent.putExtra("user_family_num", user_family_num);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        et_fam_name = (TextView) findViewById(R.id.et_fam_name);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        list_member = (ListView) findViewById(R.id.list_member);
    }


    public void initDialog() {
        //构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //标题
        builder.setTitle("提示");
        //图标
        builder.setIcon(android.R.drawable.btn_dialog);
        //内容
        builder.setMessage("未添加家庭成员！");
        //setPositiveButton（文本，点击按钮触发的事件）
        builder.setPositiveButton("现在添加", new DialogInterface.OnClickListener() {
            //int which点击的按钮的提示符
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(), RegisterFamilyActivity.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            //int which点击的按钮的提示符
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        //构建dialog对象
        dialog = builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                Intent intent = new Intent(getApplicationContext(), AddFamilyMember.class);
                intent.putExtra("user_family_num", user_family_num);
                startActivity(intent);
                break;
        }
    }
}
