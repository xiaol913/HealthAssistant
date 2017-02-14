package com.xianggao.healthassistant.entity;

import cn.bmob.v3.BmobUser;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.entity
 * 文件名：  UserData
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/14 - 0:45
 * 描述：    TODO
 */

public class UserData extends BmobUser{
    private int age;
    private boolean sex;
    private String user_name;
    private int idNumber;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }
}
