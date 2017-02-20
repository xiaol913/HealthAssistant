package com.xianggao.healthassistant.utils;

import com.xianggao.healthassistant.entity.MenuData;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ElliotGa0 on 2017/2/14.
 */

public class DataUtils {

    /**
     * 主菜单
     *
     * @param icons
     * @param names
     * @return
     */
    public static List<MenuData> getMenuList(int[] icons, String[] names) {
        List<MenuData> lists = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            MenuData menu = new MenuData(icons[i], names[i]);
            lists.add(menu);
        }
        return lists;
    }

    //MD5加密
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            return null;
        }
    }
}
