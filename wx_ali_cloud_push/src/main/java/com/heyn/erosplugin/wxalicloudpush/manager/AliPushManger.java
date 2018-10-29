package com.heyn.erosplugin.wxalicloudpush.manager;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.benmu.framework.adapter.router.RouterTracker;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.BaseEventBean;
import com.heyn.erosplugin.wxalicloudpush.event.AliPushMessage;
import com.heyn.erosplugin.wxalicloudpush.util.Constant;

/**
 * Author: heynchy
 * Date:   2018/9/28
 * <p>
 * Introduce:
 */
public class AliPushManger {

    public static boolean IS_SEND = true; // 是否全部推送
    public static int PUSH_ACTION = -1;   // 推送的类型

    /**
     * 设置发送消息的类型(通知,消息等),默认全部发送
     *
     * @param action
     */
    public static void setSendAction(int action) {
        IS_SEND = false;
        PUSH_ACTION = action;
    }

    /**
     * 设置是否将消息/通知全部回传给前端
     *
     * @param send
     */
    public static void setIsSend(boolean send) {
        IS_SEND = send;
    }

    /**
     * 初始化云推送通道
     *
     * @param context
     */
    public static void initCloudChannel(final Context context, String appKey, String appSecret) {

        final CloudPushService pushService = PushServiceFactory.getCloudPushService();
        PushServiceFactory.init(context);
        pushService.register(context, appKey, appSecret, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Log.i(Constant.TAG, "onSuccess: " + pushService.getDeviceId());
            }

            @Override
            public void onFailed(String s, String s1) {
                Log.i(Constant.TAG, "onFailed: " + s);
            }
        });
        createNotificationChannel(context);
    }

    /**
     * 针对Android O 的通知渠道做出相关处理
     *
     * @param context
     */
    private static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            // 通知渠道的id
            String id = "alipush_1";
            // 用户可以看到的通知渠道的名字.
            CharSequence name = "notification channel";
            // 用户可以看到的通知渠道的描述
            String description = "notification description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // 配置通知渠道的属性
            mChannel.setDescription(description);
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            // 设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            //最后在notificationmanager中创建该通知渠道
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }

    /**
     * 发送消息/通知信息至JS服务端
     *
     * @param message
     */
    public static void sendMsgToJs(AliPushMessage message, int action) {
        if (IS_SEND || PUSH_ACTION == action) {
            Activity activity = RouterTracker.peekActivity();
            BaseEventBean eventBean = new BaseEventBean();
            eventBean.context = activity;
            eventBean.type = WXEventCenter.EVENT_PUSHMANAGER;
            eventBean.clazzName = "com.benmu.framework.event.GlobalEvent";
            ParseManager param = ManagerFactory.getManagerService(ParseManager.class);
            String json = param.toJsonString(message);
            eventBean.param = json;
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post
                    (eventBean);
        }
    }

    /**
     * 设置通知栏图标
     * 设 置推送通知栏图标资源Bitmap。
     * 若不调用本接口，默认获取id为R.drawable.alicloud_notification_largeIcon的资源文件；
     * 若没有获取到指定图标文件，取App启动图标。
     *
     * @param icon
     */
    public static void setNotificationIcon(Bitmap icon) {
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.setNotificationLargeIcon(icon);
    }

    /**
     * 设置状态栏图标
     * 设置推送状态栏图标资源Id；
     * 若不调用本接口，默认获取id为R.drawable.alicloud_notification_smallIcon的资源文件；
     * 若没有获取到指定资源文件Id，取App启动图标。
     *
     * @param drawableId
     */
    public static void setNotificationStateIcon(int drawableId) {
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.setNotificationSmallIcon(drawableId);
    }
}
