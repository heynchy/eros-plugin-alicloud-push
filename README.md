# eros-plugin-alicloud-push
##### 基于Eros框架下的阿里云移动推送Android端的集成
       1. 可以将接受到的消息或通知内容传递至JS端以便后续的处理
       2. 可以指定某一消息的内容传递给JS端，其他的接收的消息不会传递
## Usage
### 1. Add dependency
```groovy
	dependencies {
	       implementation 'com.github.heynchy:eros-plugin-alicloud-push:0.0.4'
	}
```
### 2. 在Application中初始化移动推送（可参考demo中的位置）
```java
       /**
         * 初始化推送信息（必须设置）
         * appKey : 该应用在阿里云上的注册参数
         * appSecret： 该应用在阿里云上的注册参数
         * 将生成好的appKey 和 appSecret填入对应的位置
         */
        AliPushManger.initCloudChannel(this, "appKey", "*appSecret");
        
        /**
         *  设置是否向JS端推送消息（非必须）
         *
         *  默认为true--JS端能接受到任何消息通知
         *  设置为false--JS端不能接受到任何消息通知
         */
        AliPushManger.setIsSend(true);
        
       /**
         * 设置制定的消息类型推送至JS端（非必须）
         *
         *  PUSH_NOTIFICATION         通知的推送
         *  PUSH_MESSAGE              消息的推送
         *  PUSH_NOTIFICATION_OPEN    打开通知
         *  PUSH_NOTIFICATION_REMOVE  移除通知
         *  PUSH_NO_ACTION_OPEN       打开无跳转通知的回调
         *
         *  设置效果： 只将设置的消息类型传给JS端，其他的消息不会传给JS端
         */
        AliPushManger.setSendAction(PUSH_NOTIFICATION);
```
##### 注意：初始化必须在Application中进行，否则会报错

## 其他方法说明
    1. 设置通知栏图标
 ```java
    /**
     * 设置通知栏图标
     * 设置推送通知栏图标资源Bitmap
     * 若不调用本接口，默认获取id为R.drawable.alicloud_notification_largeIcon的资源文件
     * 若没有获取到指定图标文件，取App启动图标
     */
    AliPushManger.setNotificationIcon(Bitmap icon);
 ```
    2. 设置状态栏的图标
 ```java
    /**
     * 设置状态栏图标
     * 设置推送状态栏图标资源Id
     * 若不调用本接口，默认获取id为R.drawable.alicloud_notification_smallIcon的资源文件
     * 若没有获取到指定资源文件Id，取App启动图标
     *
     */
    AliPushManger.setNotificationStateIcon(int drawableId) ;
 ```

## Eros框架下的接收消息的位置 js/config/push.js文件中：
```java
  /**
   * 消息推送
   * options 客户端的所有消息
   */
globalEvent.addEventListener('pushMessage', function (options) {
    /** 
     *  options 包含内容主要包括  
     *        String title                  // 标题(消息，通知都有)
     *        String summary                // 简介（消息的内容，通知的简介）
     *        String extraStr               // 其他参数
     *        Map<String, String> extraMap  // 其他参数
     *        int action                    // 推送的操作(类型)
     *        String messageId              // 消息/通知的ID
     */
})
```


License
---------
    Copyright 2018 heynchy

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


