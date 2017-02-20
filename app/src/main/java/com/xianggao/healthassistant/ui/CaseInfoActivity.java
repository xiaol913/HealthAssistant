package com.xianggao.healthassistant.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.entity.CaseData;

public class CaseInfoActivity extends BaseActivity {
    private TextView case_user_name, case_user_age, case_date, case_hos_name, case_type_name, case_desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_info);
        initView();
        initData();
    }

    private void initData() {
        CaseData data = new CaseData();
        data.setUser_name(this.getIntent().getBundleExtra("case_info").getString("user_name"));
        data.setUser_age(this.getIntent().getBundleExtra("case_info").getString("user_age"));
        data.setCase_date(this.getIntent().getBundleExtra("case_info").getString("case_date"));
        data.setHos_name(this.getIntent().getBundleExtra("case_info").getString("hos_name"));
        data.setHos_part(this.getIntent().getBundleExtra("case_info").getString("hos_part"));
        data.setType_name(this.getIntent().getBundleExtra("case_info").getString("type_name"));
        data.setCase_desc(this.getIntent().getBundleExtra("case_info").getString("case_desc"));
        case_user_name.setText(data.getUser_name());
        case_user_age.setText(data.getUser_age());
        case_date.setText(data.getCase_date());
        String hos_name = data.getHos_name() + data.getHos_part();
        case_hos_name.setText(hos_name);
        case_type_name.setText(data.getType_name());
        case_desc.setText(data.getCase_desc());
    }

    private void initView() {
        case_user_name = (TextView) findViewById(R.id.case_user_name);
        case_user_age = (TextView) findViewById(R.id.case_user_age);
        case_date = (TextView) findViewById(R.id.case_date);
        case_hos_name = (TextView) findViewById(R.id.case_hos_name);
        case_type_name = (TextView) findViewById(R.id.case_type_name);
        case_desc = (TextView) findViewById(R.id.case_desc);
    }
}
