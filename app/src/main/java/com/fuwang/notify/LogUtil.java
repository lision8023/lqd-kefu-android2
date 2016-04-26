package com.fuwang.notify;

import com.fuwang.common.push.rest.pojo.MessageType;
import com.fuwang.common.push.rest.pojo.PushMessagePojo;
import com.fuwang.common.push.rest.pojo.PushType;
import com.fuwang.common.server.pojo.BaseResponse;

import java.util.logging.Logger;


/**
 * Created by win7 on 2016/4/21.
 */
public class LogUtil {
    private static Logger logger = Logger.getLogger(LogUtil.class.toString());
    public   static void  printLog(String msg){

        logger.info(msg);
    }
}
