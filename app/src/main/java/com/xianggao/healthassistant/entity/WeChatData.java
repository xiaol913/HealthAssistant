package com.xianggao.healthassistant.entity;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.entity
 * 文件名：  WeChatData
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/14 - 23:25
 * 描述：    暂时使用的微信精选
 */

public class WeChatData {
    //标题
    private String title;
    //出处
    private String source;
    //图片地址url
    private String firstImg;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }
}
