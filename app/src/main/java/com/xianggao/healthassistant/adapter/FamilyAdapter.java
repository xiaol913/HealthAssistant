package com.xianggao.healthassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.entity.FamilyData;

import java.util.List;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.adapter
 * 文件名：  FamilyAdapter
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/19 - 17:36
 * 描述：    家庭成员adapter
 */

public class FamilyAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<FamilyData> mList;
    private FamilyData data;

    public FamilyAdapter(Context mContext, List<FamilyData> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        data = mList.get(position);
        convertView = inflater.inflate(R.layout.family_item, null);
        viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        viewHolder.tv_age = (TextView) convertView.findViewById(R.id.tv_age);
        viewHolder.tv_sex = (TextView) convertView.findViewById(R.id.tv_sex);
        viewHolder.tv_name.setText(data.getUser_name());
        viewHolder.tv_age.setText(data.getUser_age());
        viewHolder.tv_sex.setText(data.getUser_sex());
        return convertView;
    }

    class ViewHolder {
        private TextView tv_name, tv_age, tv_sex;
    }
}
