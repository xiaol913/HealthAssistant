package com.xianggao.healthassistant.utils;

import android.content.Context;
import android.widget.ListView;

import com.xianggao.healthassistant.adapter.CaseAdapter;
import com.xianggao.healthassistant.adapter.FamilyAdapter;
import com.xianggao.healthassistant.adapter.QuestionAdapter;
import com.xianggao.healthassistant.adapter.ReservationAdapter;
import com.xianggao.healthassistant.entity.CaseData;
import com.xianggao.healthassistant.entity.FamilyData;
import com.xianggao.healthassistant.entity.QuestionData;
import com.xianggao.healthassistant.entity.ReservationData;
import com.xianggao.healthassistant.entity.UserData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.utils
 * 文件名：  JsonUtils
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/19 - 3:37
 * 描述：    Json工具类
 */

public class JsonUtils {

    /**
     * 登录时解析服务器回传的json
     *
     * @param session
     * @param t
     * @return
     */
    public static HashMap<String, String> parsingLogin(HashMap<String, String> session, String t) {
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
            session.put("user_id", user_id);
            session.put("user_phone_num", user_phone_num);
            session.put("user_name", user_name);
            session.put("user_id_card", user_id_card);
            session.put("user_sex", user_sex);
            session.put("user_age", user_age);
            session.put("user_address", user_address);
            session.put("user_family_num", user_family_num);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }

    /**
     * 点击显示家庭成员信息
     *
     * @param context
     * @param listView
     * @param t
     * @return
     */
    public static List<FamilyData> parsingFamily(Context context, ListView listView, List<FamilyData> mList, String t) {
        JSONArray jsonArray = null;
        String fam_name = null;
        try {
            jsonArray = new JSONArray(t);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                FamilyData data = new FamilyData();
                String user_id = json.getString("user_id");
                String user_name = json.getString("user_name");
                String user_age = json.getString("user_age");
                String user_sex = json.getString("user_sex");
                fam_name = json.getString("fam_name");
                data.setUser_id(user_id);
                data.setUser_name(user_name);
                data.setUser_age(user_age);
                data.setUser_sex(user_sex);
                data.setFam_name(fam_name);
                mList.add(data);
            }
            FamilyAdapter adapter = new FamilyAdapter(context, mList);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mList;
    }


    /**
     * 显示病人所有病例
     *
     * @param context
     * @param listView
     * @param mUserList
     * @param mCaseList
     * @param t
     * @return
     */
    public static List<UserData> parsingCase(Context context, ListView listView, List<UserData> mUserList, List<CaseData> mCaseList, String t) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(t);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                UserData userData = new UserData();
                CaseData caseData = new CaseData();
                String user_name = json.getString("user_name");
                String user_age = json.getString("user_age");
                String user_sex = json.getString("user_sex");
                String user_id_card = json.getString("user_id_card");
                String user_phone_num = json.getString("user_phone_num");
                String user_address = json.getString("user_address");
                String hos_name = json.getString("hos_name");
                String hos_part = json.getString("hos_part");
                String case_id = json.getString("case_id");
                String case_date = json.getString("case_date");
                String case_desc = json.getString("case_desc");
                String type_name = json.getString("type_name");

                userData.setUser_name(user_name);
                userData.setUser_age(user_age);
                userData.setUser_sex(user_sex);
                userData.setUser_id_card(user_id_card);
                userData.setUser_phone_num(user_phone_num);
                userData.setUser_address(user_address);

                caseData.setUser_name(user_name);
                caseData.setUser_age(user_age);
                caseData.setHos_name(hos_name);
                caseData.setHos_part(hos_part);
                caseData.setType_name(type_name);
                caseData.setCase_id(case_id);
                caseData.setCase_date(case_date);
                caseData.setCase_desc(case_desc);

                mCaseList.add(caseData);
                mUserList.add(userData);
            }
            CaseAdapter adapter = new CaseAdapter(context, mCaseList);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mUserList;
    }


    /**
     * 显示病人所有预约
     *
     * @param context
     * @param listView
     * @param mList
     * @param t
     */
    public static void parsingReservation(Context context, ListView listView, List<ReservationData> mList, String t) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(t);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                ReservationData data = new ReservationData();
                data.setRes_end(json.getString("res_end"));
                data.setHos_name(json.getString("hos_name"));
                data.setHos_part(json.getString("hos_part"));
                data.setType_name(json.getString("type_name"));
                data.setStatus_name(json.getString("status_name"));
                data.setUser_name(json.getString("user_name"));

                mList.add(data);
            }
            ReservationAdapter adapter = new ReservationAdapter(context, mList);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析问题类
     *
     * @param context
     * @param listView
     * @param mList
     * @param t
     */
    public static void parsingQuestion(Context context, ListView listView, List<QuestionData> mList, String t) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(t);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                QuestionData data = new QuestionData();
                data.setAsk_time(json.getString("ask_time"));
                data.setAsk_title(json.getString("ask_title"));
                data.setAsk_name(json.getString("ask_name"));
                data.setAsk_age(json.getString("ask_age"));
                data.setAsk_sex(json.getString("ask_sex"));
                data.setAsk_desc(json.getString("ask_desc"));

                mList.add(data);
            }
            QuestionAdapter adapter = new QuestionAdapter(context, mList);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
