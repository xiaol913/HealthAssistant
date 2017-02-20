package com.xianggao.healthassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.entity.ReservationData;

import java.util.List;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.adapter
 * 文件名：  ReservationAdapter
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/20 - 4:24
 * 描述：    预约adapter
 */

public class ReservationAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ReservationData> mList;
    private ReservationData data;

    public ReservationAdapter(Context mContext, List<ReservationData> mList) {
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
        ViewHolder viewHolder = new ReservationAdapter.ViewHolder();
        data = mList.get(position);
        convertView = inflater.inflate(R.layout.reservation_item, null);
        viewHolder.tv_R_user = (TextView) convertView.findViewById(R.id.tv_R_user);
        viewHolder.tv_R_date = (TextView) convertView.findViewById(R.id.tv_R_date);
        viewHolder.tv_R_name = (TextView) convertView.findViewById(R.id.tv_R_name);
        viewHolder.tv_R_type = (TextView) convertView.findViewById(R.id.tv_R_type);
        viewHolder.tv_R_status = (TextView) convertView.findViewById(R.id.tv_R_status);

        String name = data.getHos_name() + data.getHos_part();
        viewHolder.tv_R_user.setText(data.getUser_name());
        viewHolder.tv_R_date.setText(data.getRes_end());
        viewHolder.tv_R_name.setText(name);
        viewHolder.tv_R_type.setText(data.getType_name());
        viewHolder.tv_R_status.setText(data.getStatus_name());
        return convertView;
    }

    class ViewHolder {
        private TextView tv_R_user, tv_R_date, tv_R_name, tv_R_type, tv_R_status;
    }
}
