package com.heyn.erosplugin.wxalicloudpush;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.google.gson.Gson;
import com.heyn.erosplugin.wxalicloudpush.event.AliPushMessage;
import com.heyn.erosplugin.wxalicloudpush.manager.AliPushManger;
import com.heyn.erosplugin.wxalicloudpush.util.Constant;

import java.util.Map;

import static com.heyn.erosplugin.wxalicloudpush.util.Constant.PUSH_MESSAGE;
import static com.heyn.erosplugin.wxalicloudpush.util.Constant.PUSH_NOTIFICATION;
import static com.heyn.erosplugin.wxalicloudpush.util.Constant.PUSH_NOTIFICATION_OPEN;
import static com.heyn.erosplugin.wxalicloudpush.util.Constant.PUSH_NOTIFICATION_REMOVE;
import static com.heyn.erosplugin.wxalicloudpush.util.Constant.PUSH_NO_ACTION_OPEN;


/**
 * Author: heynchy
 * Date:   2018/9/26
 * <p>
 * Introduce:
 */
public class AliPushReceiver extends MessageReceiver {

    @Override
    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        // TODO 处理推送通知
        AliPushMessage message = new AliPushMessage.Bulider()
                .setAction(PUSH_NOTIFICATION)
                .setTitle(title)
                .setSummary(summary)
                .setExtraMap(extraMap)
                .create();
        AliPushManger.sendMsgToJs(message, PUSH_NOTIFICATION);
        Log.i(Constant.TAG, "onNotification: " + new Gson().toJson(message));

    }

    @Override
    public void onMessage(Context context, CPushMessage cPushMessage) {
        Log.i(Constant.TAG, "onMessage: " + cPushMessage.getContent());
        // TODO 处理推送的消息
        AliPushMessage message = new AliPushMessage.Bulider()
                .setAction(PUSH_MESSAGE)
                .setTitle(cPushMessage.getTitle())
                .setSummary(cPushMessage.getContent())
                .setMessageId(cPushMessage.getMessageId())
                .create();
        AliPushManger.sendMsgToJs(message, PUSH_MESSAGE);
    }

    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        Log.i(Constant.TAG, "onNotificationOpened");
        // TODO 点击打开通知后的回调
        AliPushMessage message = new AliPushMessage.Bulider()
                .setAction(PUSH_NOTIFICATION_OPEN)
                .setTitle(title)
                .setSummary(summary)
                .setExtraStr(extraMap)
                .create();
        AliPushManger.sendMsgToJs(message, PUSH_NOTIFICATION_OPEN);
    }

    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        Log.i(Constant.TAG, "onNotificationClickedWithNoAction");
        // TODO 打开通知后无其他操作的回调(无跳转的通知类型)
        AliPushMessage message = new AliPushMessage.Bulider()
                .setAction(PUSH_NO_ACTION_OPEN)
                .setTitle(title)
                .setSummary(summary)
                .setExtraStr(extraMap)
                .create();
        AliPushManger.sendMsgToJs(message, PUSH_NO_ACTION_OPEN);
    }

    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        Log.i(Constant.TAG, "onNotificationReceivedInApp");
        // TODO 当用户创建自定义通知样式，并且设置推送应用内到达不创建通知弹窗时调用该回调，且此时不调用onNotification回调
    }

    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        AliPushMessage message = new AliPushMessage.Bulider()
                .setAction(PUSH_NOTIFICATION_REMOVE)
                .setMessageId(messageId)
                .create();
        AliPushManger.sendMsgToJs(message, PUSH_NOTIFICATION_REMOVE);
        Log.i(Constant.TAG, "onNotificationRemoved");
    }
}
