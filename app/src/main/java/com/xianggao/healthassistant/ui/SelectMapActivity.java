package com.xianggao.healthassistant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xianggao.healthassistant.R;

public class SelectMapActivity extends BaseActivity implements View.OnClickListener {
    private Button button;
    private String user_id;
    private String user_name;
    private String type_id;
    private String res_end;
    private String type_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_map);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        initData();
    }

    private void initData() {
        user_id = this.getIntent().getStringExtra("user_id");
        user_name = this.getIntent().getStringExtra("user_name");
        type_name = this.getIntent().getStringExtra("type_name");
        type_id = this.getIntent().getStringExtra("type_id");
        res_end = this.getIntent().getStringExtra("res_end");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(v.getContext(), PayActivity.class);
                intent.putExtra("user_id", user_id);
                intent.putExtra("type_id", type_id);
                intent.putExtra("res_end", res_end);
                intent.putExtra("user_name", user_name);
                intent.putExtra("type_name", type_name);
                startActivity(intent);
                finish();
                break;
        }
    }
}
