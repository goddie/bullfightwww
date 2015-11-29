package com.xiaba2.core;

import java.util.logging.Logger;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtil {

	
	public static void push(String alias,String content)
	{
		JPushClient jpushClient = new JPushClient("6fa865d06b7b0d097180447d", "d3c585b3246103fc4f25a16b", 3);

		PushPayload payload = null;
		
		if(alias==null)
		{
			 payload = buildPushObject_all_all_alert(content);
		}else
		{
			 payload = buildPushObject_all_alias_alert(alias,content);
		}
        // For push, all you need do is to build PushPayload object.
        

        try {
            PushResult result = jpushClient.sendPush(payload);
            Logger.getLogger(JPushUtil.class.getName()).info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
        	Logger.getLogger(JPushUtil.class.getName()).info("Connection error, should retry later"+e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
        	Logger.getLogger(JPushUtil.class.getName()).info("Should review the error, and fix the request"+e);
        	Logger.getLogger(JPushUtil.class.getName()).info("HTTP Status: " + e.getStatus());
        	Logger.getLogger(JPushUtil.class.getName()).info("Error Code: " + e.getErrorCode());
        	Logger.getLogger(JPushUtil.class.getName()).info("Error Message: " + e.getErrorMessage());
        }

		
	}
	
	
	public static void push(String content)
	{
		push(null,content);
	}
	
	
	public static PushPayload buildPushObject_all_all_alert(String content) {
        return PushPayload.alertAll(content);
    }
	
	public static PushPayload buildPushObject_all_alias_alert(String alias,String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(content))
                .build();
    }
	
}
