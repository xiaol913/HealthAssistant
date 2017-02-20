package com.xianggao.healthassistant.entity;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.entity
 * 文件名：  QuestionData
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/20 - 5:28
 * 描述：    问题基类
 */

public class QuestionData {
    private String ask_time;
    private String ask_sex;
    private String ask_title;
    private String ask_name;
    private String ask_age;
    private String ask_desc;

    public QuestionData() {
    }

    public QuestionData(String ask_time, String ask_sex, String ask_title, String ask_name, String ask_age, String ask_desc) {
        this.ask_time = ask_time;
        this.ask_sex = ask_sex;
        this.ask_title = ask_title;
        this.ask_name = ask_name;
        this.ask_age = ask_age;
        this.ask_desc = ask_desc;
    }

    public String getAsk_time() {
        return ask_time;
    }

    public void setAsk_time(String ask_time) {
        this.ask_time = ask_time;
    }

    public String getAsk_sex() {
        return ask_sex;
    }

    public void setAsk_sex(String ask_sex) {
        this.ask_sex = ask_sex;
    }

    public String getAsk_title() {
        return ask_title;
    }

    public void setAsk_title(String ask_title) {
        this.ask_title = ask_title;
    }

    public String getAsk_name() {
        return ask_name;
    }

    public void setAsk_name(String ask_name) {
        this.ask_name = ask_name;
    }

    public String getAsk_age() {
        return ask_age;
    }

    public void setAsk_age(String ask_age) {
        this.ask_age = ask_age;
    }

    public String getAsk_desc() {
        return ask_desc;
    }

    public void setAsk_desc(String ask_desc) {
        this.ask_desc = ask_desc;
    }
}
