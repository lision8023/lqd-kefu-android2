package com.fuwang.notify;



import com.fuwang.android.common.http.BaseAPIClient;
//import com.qiandai.lqd.android.global.AppConfig;
//import com.qiandai.lqd.android.global.MyAccount;

import java.util.HashMap;
import java.util.Map;


public abstract class BaseAPI {

    protected Map<String,String> defaultHeaders = new HashMap<String, String>();
    protected Map<String,String> defaultQueryParam = new HashMap<String, String>();

    protected BaseAPIClient apiClient;

    public BaseAPI(String rootUrl){
        this.apiClient = BaseAPIClient.getInstance(rootUrl, AppConfig.secretKey);
//        this.apiClient = BaseAPIClient.getInstance(rootUrl, "");
        initDefault();
    }

    /**
     * 初始化默认的参数，该方法在需要重新初始化默认参数时调用，比如当用户认证成功后，需要更新当前用户的token。
     */
    protected void initDefault(){
       apiClient.addDefaultHeaders("accept","application/json");
        //每个请求默认均需要包含Token和AppKey

        apiClient.addDefaultQueryParams("appkey",AppConfig.appKey);
    }


}
