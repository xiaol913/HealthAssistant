package com.xianggao.healthassistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xianggao.healthassistant.R;
import com.xianggao.healthassistant.entity.MenuData;

import java.util.List;

/**
 * Created by ElliotGa0 on 2017/2/14.
 */

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuViewHolder> {
    protected Context context;
    protected List<MenuData> mainMenu;

    public MainMenuAdapter(Context context, List<MenuData> mainMenu) {
        this.mainMenu = mainMenu;
        this.context = context;
    }

    @Override
    public MainMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainMenuViewHolder(LayoutInflater.from(context).inflate(R.layout.main_item, null));
    }

    @Override
    public void onBindViewHolder(final MainMenuViewHolder holder, int position) {
        MenuData menuData = mainMenu.get(position);
        holder.menu_icon.setImageResource(menuData.icon);
        holder.menu_text.setText(menuData.menuName);
        //判断是是否点击
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, layoutPos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return null != mainMenu ? mainMenu.size() : 0;
    }

    //点击事件
    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    public onItemClickListener onItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}


class MainMenuViewHolder extends RecyclerView.ViewHolder {
    public ImageView menu_icon;
    public TextView menu_text;

    public MainMenuViewHolder(View itemView) {
        super(itemView);
        menu_icon = (ImageView) itemView.findViewById(R.id.menu_icon);
        menu_text = (TextView) itemView.findViewById(R.id.menu_text);
    }

}