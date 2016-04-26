package com.fuwang.notify;

import com.fuwang.android.common.http.IResponseCallback;
import com.fuwang.common.server.pojo.BaseResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lufeisong on 16/3/31.
 */
public class PushAPI extends BaseAPI {
    public PushAPI() {

        super(AppConfig.pushRootUrl);

    }

    /**
     * 终端绑定
     *
     * @param channelId     百度channelID
     * @param thirdUserId   百度用户ID
     * @param ownUserId     本系统用户ID
     * @param deviceType    设备类型
     * @param appKey        百度APPKey
     * @param applicationId 本系统应用ID
     * @param callback
     */
    public void postBind(String channelId, String thirdUserId, String ownUserId, String deviceType, String appKey, String applicationId,String pushType, IResponseCallback<BaseResponse> callback) {
        Map<String, String> formParam = new HashMap<String, String>();
        formParam.put("channelId", channelId);
        formParam.put("thirdUserId", thirdUserId);
        formParam.put("ownUserId", ownUserId);
        formParam.put("deviceType", deviceType);
        formParam.put("appKey", appKey);
        formParam.put("pushType", pushType);
        formParam.put("applicationId", applicationId);
        apiClient.post("/pushServer/bind", null, formParam, callback, BaseResponse.class);
    }
}
