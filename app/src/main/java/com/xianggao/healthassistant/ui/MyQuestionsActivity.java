package com.xianggao.healthassistant.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.entity.QuestionData;
import com.xianggao.healthassistant.utils.JsonUtils;
import com.xianggao.healthassistant.utils.StaticClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyQuestionsActivity extends BaseActivity {
    private ListView list_question;
    private AlertDialog dialog;
    private HashMap<String, String> session;
    private List<QuestionData> mList = new ArrayList<>();
    private String url = StaticClass.HTTP+"getQue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_questions);
        initView();
        initDialog();
        initData();
    }

    private void initData() {
        session = (HashMap<String, String>) this
                .getIntent()
                .getBundleExtra("session")
                .getSerializable("sessionId");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_id", session.get("user_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpParams params = new HttpParams();
        params.putJsonParams(jsonObject.toString());
        RxVolley.jsonPost(url, params, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                if (t.equals("No data")) {
                    if (dialog != null && !dialog.isShowing()) {
                        dialog.show();
                    }
                } else {
                    JsonUtils.parsingQuestion(getApplicationContext(), list_question, mList, t);
                }
            }
        });
        list_question.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), QuestionInfoActivity.class);
                intent.putExtra("ask_name", mList.get(position).getAsk_name());
                intent.putExtra("ask_time", mList.get(position).getAsk_time());
                intent.putExtra("ask_age", mList.get(position).getAsk_age());
                intent.putExtra("ask_sex", mList.get(position).getAsk_sex());
                intent.putExtra("ask_desc", mList.get(position).getAsk_desc());
                intent.putExtra("ask_title", mList.get(position).getAsk_title());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        list_question = (ListView) findViewById(R.id.list_question);
    }


    public void initDialog() {
        //构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //标题
        builder.setTitle("提示");
        //图标
        builder.setIcon(android.R.drawable.btn_dialog);
        //内容
        builder.setMessage("无任何问题！");
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
