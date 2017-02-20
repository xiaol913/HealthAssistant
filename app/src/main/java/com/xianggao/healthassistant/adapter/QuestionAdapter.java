package com.xianggao.healthassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.entity.QuestionData;

import java.util.List;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.adapter
 * 文件名：  QuestionAdapter
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/20 - 5:32
 * 描述：    问题类adapter
 */

public class QuestionAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<QuestionData> mList;
    private QuestionData data;

    public QuestionAdapter(Context mContext, List<QuestionData> mList) {
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
        ViewHolder viewHolder = new QuestionAdapter.ViewHolder();
        data = mList.get(position);
        convertView = inflater.inflate(R.layout.question_item, null);
        viewHolder.tv_Q_time = (TextView) convertView.findViewById(R.id.tv_Q_time);
        viewHolder.tv_Q_title = (TextView) convertView.findViewById(R.id.tv_Q_title);
        viewHolder.tv_Q_count = (TextView) convertView.findViewById(R.id.tv_Q_count);
        viewHolder.tv_Q_time.setText(data.getAsk_time());
        viewHolder.tv_Q_title.setText(data.getAsk_title());
        return convertView;
    }

    class ViewHolder {
        private TextView tv_Q_time, tv_Q_title, tv_Q_count;
    }
}
