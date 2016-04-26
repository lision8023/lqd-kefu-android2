package com.fuwang.notify;


public interface PushModel {
    void bindBaiduService();
    void unBindBaiduService();
    void bindLqdService(String channelId, String thirdUserId);
}
