package com.xianggao.healthassistant.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.utils
 * 文件名：  JsonUtils
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/19 - 3:37
 * 描述：    Json工具类
 */

public class JsonUtils {

    //解析Json
    public static HashMap<String, String> parsingJson(HashMap<String, String> session,String t) {
        JSONObject json = null;
        try {
            json = new JSONObject(t);
            String user_id = json.getString("user_id");
            String user_phone_num = json.getString("user_phone_num");
            String user_name = json.getString("user_name");
            String user_id_card = json.getString("user_id_card");
            String user_sex = json.getString("user_sex");
            String user_age = json.getString("user_age");
            String user_address = json.getString("user_address");
            String user_family_num = json.getString("user_family_num");
            session.put("user_id",user_id);
            session.put("user_phone_num",user_phone_num);
            session.put("user_name",user_name);
            session.put("user_id_card",user_id_card);
            session.put("user_sex",user_sex);
            session.put("user_age",user_age);
            session.put("user_address",user_address);
            session.put("user_family_num",user_family_num);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }
}
