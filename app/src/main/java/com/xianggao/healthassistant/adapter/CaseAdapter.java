package com.xianggao.healthassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.entity.CaseData;

import java.util.List;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.adapter
 * 文件名：  CaseAdapter
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/19 - 20:49
 * 描述：    CaseAdapter类
 */

public class CaseAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater inflater;
    private List<CaseData> mCaseList;
    private CaseData caseData;

    public CaseAdapter(Context mContext,List<CaseData> mCaseList){
        this.mContext = mContext;
        this.mCaseList = mCaseList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mCaseList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCaseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new CaseAdapter.ViewHolder();
        caseData = mCaseList.get(position);
        convertView = inflater.inflate(R.layout.family_item, null);
        viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        viewHolder.tv_age = (TextView) convertView.findViewById(R.id.tv_age);
        viewHolder.tv_sex = (TextView) convertView.findViewById(R.id.tv_sex);
        viewHolder.tv_name.setText(caseData.getCase_date());
        String partName = caseData.getHos_name()+caseData.getHos_part();
        viewHolder.tv_age.setText(partName);
        viewHolder.tv_sex.setText(caseData.getType_name());
        return convertView;
    }

    class ViewHolder{
        private TextView tv_name, tv_age, tv_sex;
    }
}
