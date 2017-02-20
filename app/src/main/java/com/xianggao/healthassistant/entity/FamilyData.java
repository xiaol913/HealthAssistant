package com.xianggao.healthassistant.entity;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.entity
 * 文件名：  FamilyData
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/19 - 17:37
 * 描述：    家庭成员基础类
 */

public class FamilyData {
    private String user_id;
    private String user_name;
    private String user_age;
    private String user_sex;
    private String fam_name;


    public String getFam_name() {
        return fam_name;
    }

    public void setFam_name(String fam_name) {
        this.fam_name = fam_name;
    }

    public FamilyData() {
    }

    public FamilyData(String user_id, String user_name, String user_age, String user_sex, String fam_name) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_age = user_age;
        this.user_sex = user_sex;
        this.fam_name = fam_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

}
