package com.xianggao.healthassistant.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.entity.QuestionData;

public class QuestionInfoActivity extends BaseActivity {
    private TextView tv_ques_name, tv_ques_sex, tv_ques_age, tv_ques_time, tv_ques_title, tv_ques_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_info);
        initView();
        initData();
    }

    private void initData() {
        QuestionData data = new QuestionData();
        data.setAsk_name(this.getIntent().getStringExtra("ask_name"));
        data.setAsk_age(this.getIntent().getStringExtra("ask_age"));
        data.setAsk_sex(this.getIntent().getStringExtra("ask_sex"));
        data.setAsk_desc(this.getIntent().getStringExtra("ask_desc"));
        data.setAsk_title(this.getIntent().getStringExtra("ask_title"));
        data.setAsk_time(this.getIntent().getStringExtra("ask_time"));

        tv_ques_name.setText(data.getAsk_name().toString().trim());
        tv_ques_sex.setText(data.getAsk_sex().toString().trim());
        tv_ques_age.setText(data.getAsk_age().toString().trim());
        tv_ques_time.setText(data.getAsk_time().toString().trim());
        tv_ques_title.setText(data.getAsk_title().toString().trim());
        tv_ques_desc.setText(data.getAsk_desc().toString().trim());

    }

    private void initView() {
        tv_ques_name = (TextView) findViewById(R.id.tv_ques_name);
        tv_ques_sex = (TextView) findViewById(R.id.tv_ques_sex);
        tv_ques_age = (TextView) findViewById(R.id.tv_ques_age);
        tv_ques_time = (TextView) findViewById(R.id.tv_ques_time);
        tv_ques_title = (TextView) findViewById(R.id.tv_ques_title);
        tv_ques_desc = (TextView) findViewById(R.id.tv_ques_desc);
    }


}
