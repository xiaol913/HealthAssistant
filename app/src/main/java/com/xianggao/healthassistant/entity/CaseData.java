package com.xianggao.healthassistant.entity;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.entity
 * 文件名：  CaseData
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/19 - 20:32
 * 描述：    病例类
 */

public class CaseData {
    private String case_id;
    private String user_name;
    private String user_age;
    private String case_date;
    private String case_desc;
    private String type_name;
    private String hos_name;
    private String hos_part;

    public CaseData() {
    }

    public String getCase_id() {
        return case_id;
    }

    public void setCase_id(String case_id) {
        this.case_id = case_id;
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

    public String getCase_date() {
        return case_date;
    }

    public void setCase_date(String case_date) {
        this.case_date = case_date;
    }

    public String getCase_desc() {
        return case_desc;
    }

    public void setCase_desc(String case_desc) {
        this.case_desc = case_desc;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getHos_name() {
        return hos_name;
    }

    public void setHos_name(String hos_name) {
        this.hos_name = hos_name;
    }

    public String getHos_part() {
        return hos_part;
    }

    public void setHos_part(String hos_part) {
        this.hos_part = hos_part;
    }

    public CaseData(String case_id, String user_name, String user_age, String case_date, String case_desc, String type_name, String hos_name, String hos_part) {
        this.case_id = case_id;
        this.user_name = user_name;
        this.user_age = user_age;
        this.case_date = case_date;
        this.case_desc = case_desc;
        this.type_name = type_name;
        this.hos_name = hos_name;
        this.hos_part = hos_part;
    }
}
