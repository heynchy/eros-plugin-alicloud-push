package com.heyn.erosplugin.alicloudpush;

import android.app.Application;
import android.util.Log;

import com.heyn.erosplugin.wxalicloudpush.AliPushReceiver;
import com.heyn.erosplugin.wxalicloudpush.manager.AliPushManger;

import static com.heyn.erosplugin.wxalicloudpush.util.Constant.PUSH_NOTIFICATION;

/**
 * Author: 崔海营
 * Date:   2018/9/28
 * <p>
 * Introduce:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Ali_push","main Application");
        /**
         * 初始化推送信息
         * appKey : 该应用在阿里云上的注册参数
         * appSecret： 该应用在阿里云上的注册参数
         */
        AliPushManger.initCloudChannel(this, "****", "******");

        // 设置是否向JS端推送消息 (不设置的话默认为true, 设置为false JS端不能接受到任何消息通知)
        /**
         * 设置是否向JS端推送消息
         *  默认为true--JS端能接受到任何消息通知
         *  设置为false--JS端不能接受到任何消息通知
         */
        AliPushManger.setIsSend(true);
        //
        /**
         * 设置制定的消息类型推送至JS端
         *
         *  PUSH_NOTIFICATION         通知的推送
         *  PUSH_MESSAGE;             消息的推送
         *  PUSH_NOTIFICATION_OPEN    打开通知
         *  PUSH_NOTIFICATION_REMOVE  移除通知
         *  PUSH_NO_ACTION_OPEN       打开无跳转通知的回调
         *
         *  设置效果： 只将设置的消息类型传给JS端，其他的消息不会传给JS端
         */
        AliPushManger.setSendAction(PUSH_NOTIFICATION);
    }
}
