package com.heyn.erosplugin.wxalicloudpush;



import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.weex.plugin.annotation.WeexModule;
import com.google.gson.Gson;
import com.heyn.erosplugin.wxalicloudpush.event.PushEvent;
import com.heyn.erosplugin.wxalicloudpush.manager.AliPushManger;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;



/**
 * Author: Heynchy
 * Date:   2018/10/9
 * <p>
 * Introduce: Android 与 Eros交互所用到的日常工具类
 */
@WeexModule(name = "AliPushModule", lazyLoad = true)
public class AliPushModule extends WXModule {

    /**
     * 移动推送，绑定账号
     */
    @JSMethod(uiThread = true)
    public void bindAccount(String params, final JSCallback susscess, final JSCallback failure) {
        // 强制退出程序
        if (TextUtils.isEmpty(params)) {
            failure.invoke("请提供要绑定的信息");
            return;
        }
        PushEvent paramsEvent = new Gson().fromJson(params, PushEvent.class);
        String amount = paramsEvent.getAccount();
        AliPushManger.getPushService().bindAccount(amount, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                susscess.invoke(s);
            }

            @Override
            public void onFailed(String s, String s1) {
                failure.invoke(s);
            }
        });
    }

    /**
     * 移动推送解绑账号
     */
    @JSMethod(uiThread = true)
    public void unbindAccount(){
        AliPushManger.getPushService().unbindAccount(new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Log.i("Ali_PUSH","移动推送解绑成功");
            }

            @Override
            public void onFailed(String s, String s1) {
                Log.i("Ali_PUSH","移动推送解绑失败");
            }
        });
    }
}
