package com.xianggao.healthassistant.ui;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.xianggao.healthassistant.R;

public class DoReservationsActivity extends BaseActivity implements View.OnClickListener {
    private AlertDialog alertDialog;
    private String user_id;
    private String user_name;

    private TextView txt_R_name, txt_R_type, txt_R_time;
    private Button btn_R_type, btn_R_time, btn_R_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_reservations);
        initView();
        initData();
    }

    private void initView() {
        txt_R_name = (TextView) findViewById(R.id.txt_R_name);
        txt_R_type = (TextView) findViewById(R.id.txt_R_type);
        txt_R_time = (TextView) findViewById(R.id.txt_R_time);
        btn_R_type = (Button) findViewById(R.id.btn_R_type);
        btn_R_type.setOnClickListener(this);
        btn_R_time = (Button) findViewById(R.id.btn_R_time);
        btn_R_time.setOnClickListener(this);
        btn_R_submit = (Button) findViewById(R.id.btn_R_submit);
        btn_R_submit.setOnClickListener(this);
    }

    private void initData() {
        user_id = this.getIntent().getStringExtra("user_id");
        user_name = this.getIntent().getStringExtra("user_name");
        txt_R_name.setText(user_name);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_R_type:
                showSingleListDialog();
                break;
            case R.id.btn_R_time:
                showSettingDateDialog();
                break;
            case R.id.btn_R_submit:
                Intent intent = new Intent(v.getContext(), SelectMapActivity.class);
                intent.putExtra("user_id", user_id);
                intent.putExtra("user_name", user_name);
                String type_id = null;
                String type_name = txt_R_type.getText().toString();
                if (type_name.equals("耳鼻喉科")) {
                    type_id = "1";
                } else {
                    type_id = "2";
                }
                intent.putExtra("type_name", type_name);
                intent.putExtra("type_id", type_id);
                intent.putExtra("res_end", txt_R_time.getText().toString().trim());
                startActivity(intent);
                finish();
                break;
        }
    }

    /**
     * 显示设置时间对话框
     */
    public void showSettingDateDialog() {
        //实例化DatePickerDialog，并使用匿名内方法监听器
        DatePickerDialog dll = new DatePickerDialog(DoReservationsActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String time = i + "-" + (i1 + 1) + "-" + i2;
                txt_R_time.setText(time);
            }
        }, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
        dll.show();
    }

    /**
     * 显示单选列表对话框
     */
    public void showSingleListDialog() {
        //实例化 AlertDialog.Builder 对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置dialog title
        builder.setTitle("请选择种类");
        //读取单选列表text内容array
        final String[] singleValue = getResources().getStringArray(R.array.type_list);
        builder.setSingleChoiceItems(R.array.type_list, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                txt_R_type.setText(singleValue[i]);
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }
}
