package com.fuwang.notify;

import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.MediaStore;

import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.fuwang.android.common.http.IResponseCallback;
import com.fuwang.common.server.exception.error.IError;
import com.fuwang.common.server.pojo.BaseResponse;

public class IPush implements PushModel {
    private Context _ctx;
    private PushAPI _pushAPI = new PushAPI();
    final String API_KEY = "5IgEAYnLD0QGqzQC8jl2Scit";

    public IPush() {

    }

    public IPush(Context ctx) {
        _ctx = ctx;
    }

    @Override
    public void bindBaiduService() {

        if (!PushManager.isPushEnabled(_ctx)) {
            LogUtil.printLog("bindBaiduService :  bind");
            Resources resource = _ctx.getResources();
            String pkgName = _ctx.getPackageName();
            PushManager.startWork(_ctx, PushConstants.LOGIN_TYPE_API_KEY, API_KEY);
            CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
                    resource.getIdentifier(
                            "notification_custom_builder", "layout", pkgName),
                    resource.getIdentifier("notification_icon", "id", pkgName),
                    resource.getIdentifier("notification_title", "id", pkgName),
                    resource.getIdentifier("notification_text", "id", pkgName));
            cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
            cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
            cBuilder.setStatusbarIcon(_ctx.getApplicationInfo().icon);
            cBuilder.setLayoutDrawable(resource.getIdentifier(
                    "simple_notification_icon", "drawable", pkgName));
            cBuilder.setNotificationSound(Uri.withAppendedPath(
                    MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6").toString());
            // 推送高级设置，通知栏样式设置为下面的ID
            PushManager.setNotificationBuilder(_ctx, 1, cBuilder);
        }else{
            LogUtil.printLog("bindBaiduService : already bind");
        }

    }

    @Override
    public void unBindBaiduService() {
        if (PushManager.isPushEnabled(_ctx)){
            LogUtil.printLog("unBindBaiduService : bind");

            PushManager.stopWork(_ctx);
        }
        else
            LogUtil.printLog("unBindBaiduService : not bind");
    }

    @Override
    public void bindLqdService(String channelId, String thirdUserId) {
        requestBindLqdService(channelId, thirdUserId);
    }

    void requestBindLqdService(String channelId, String thirdUserId) {
//        _pushAPI.postBind(channelId, thirdUserId, MyAccount.getInstance().getUserId(), AppConfig.getInstance().getUpdatetype(), API_KEY, AppConfig.getInstance().getApplicationId(), AppConfig.getInstance().getPushType(), bindBaseResponse);
        _pushAPI.postBind(channelId, thirdUserId,AppConfig.ownUserId, AppConfig.deviceType, API_KEY, AppConfig.applicationId, AppConfig.pushType, bindBaseResponse);
    }

    IResponseCallback<BaseResponse> bindBaseResponse = new IResponseCallback<BaseResponse>() {
        @Override
        public void onSuccess(BaseResponse baseResponse) {
            String responseStatus = baseResponse.getStatus().toString();
            if (responseStatus.equals("SUCCEED")) {
                LogUtil.printLog("BindLqdService succeed");
            } else if (responseStatus.equals("FAILED")) {
                LogUtil.printLog("BindLqdService failed : errorCode = " + baseResponse.getErrorCode()
                        + " ; errorMessage = " + baseResponse.getErrorMessage()
                        + " ; extMessage = " + baseResponse.getExtMessage());
            }
        }

        @Override
        public void onFailure(int i, String s) {
            LogUtil.printLog("BindLqdService onFailure : i = " + i + " ; s = " + s);
        }

        @Override
        public void onError(IError iError) {
            LogUtil.printLog("BindLqdService onError : " + iError.toString());
        }
    };
}
