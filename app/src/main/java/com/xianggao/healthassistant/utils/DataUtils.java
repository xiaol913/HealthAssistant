package com.xianggao.healthassistant.utils;

import com.xianggao.healthassistant.entity.MenuData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ElliotGa0 on 2017/2/14.
 */

public class DataUtils {

    /**
     * 主菜单
     * @param icons
     * @param names
     * @return
     */
    public static List<MenuData> getMenuList(int[] icons, String[] names){
        List<MenuData> lists=new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            MenuData menu=new MenuData(icons[i],names[i]);
            lists.add(menu);
        }
        return lists;
    }
}
