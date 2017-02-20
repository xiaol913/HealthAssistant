package com.xianggao.healthassistant.entity;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.entity
 * 文件名：  ReservationData
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/20 - 4:16
 * 描述：    预约基础类
 */

public class ReservationData {
    private String res_end;
    private String hos_name;
    private String hos_part;
    private String type_name;
    private String status_name;
    private String user_name;

    public ReservationData() {
    }

    public ReservationData(String res_end, String hos_name, String hos_part, String type_name, String status_name, String user_name) {
        this.res_end = res_end;
        this.hos_name = hos_name;
        this.user_name = user_name;
        this.hos_part = hos_part;
        this.type_name = type_name;
        this.status_name = status_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRes_end() {
        return res_end;
    }

    public void setRes_end(String res_end) {
        this.res_end = res_end;
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

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
