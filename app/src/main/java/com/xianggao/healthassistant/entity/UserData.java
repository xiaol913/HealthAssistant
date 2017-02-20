package com.xianggao.healthassistant.entity;

import cn.bmob.v3.BmobUser;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.entity
 * 文件名：  UserData
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/14 - 0:45
 * 描述：    用户类
 */

public class UserData extends BmobUser {
    private String user_phone_num;
    private String user_name;
    private String user_id_card;
    private String user_age;
    private String user_sex;
    private String user_address;

    public UserData() {
    }

    public UserData(String user_phone_num, String user_name, String user_id_card, String user_age, String user_sex, String user_address) {
        this.user_phone_num = user_phone_num;
        this.user_name = user_name;
        this.user_id_card = user_id_card;
        this.user_age = user_age;
        this.user_sex = user_sex;
        this.user_address = user_address;
    }

    public String getUser_phone_num() {
        return user_phone_num;
    }

    public void setUser_phone_num(String user_phone_num) {
        this.user_phone_num = user_phone_num;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id_card() {
        return user_id_card;
    }

    public void setUser_id_card(String user_id_card) {
        this.user_id_card = user_id_card;
    }

    public String getUser_age() {
        return user_age;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }
}
