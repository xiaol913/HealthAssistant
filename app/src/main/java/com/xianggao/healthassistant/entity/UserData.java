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

public class UserData extends BmobUser{
    private int user_phone_num;
    private String user_name;
    private int user_id_card;
    private int user_age;
    private String user_sex;
    private String user_address;
    private String user_password;

    public int getUser_phone_num() {
        return user_phone_num;
    }

    public void setUser_phone_num(int user_phone_num) {
        this.user_phone_num = user_phone_num;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_id_card() {
        return user_id_card;
    }

    public void setUser_id_card(int user_id_card) {
        this.user_id_card = user_id_card;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
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

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
