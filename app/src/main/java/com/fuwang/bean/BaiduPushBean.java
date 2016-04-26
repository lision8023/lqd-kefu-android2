package com.fuwang.bean;

/**
 * Created by lufeisong on 16/3/31.
 */
public class BaiduPushBean extends BaseBean {
    String title; //推送的通知的标题
    String description;//推送的通知的描述
    String customContentString;//自定义内容，为空或者json字符串
    String time;//存放的时间

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomContentString() {
        return customContentString;
    }

    public void setCustomContentString(String customContentString) {
        this.customContentString = customContentString;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
