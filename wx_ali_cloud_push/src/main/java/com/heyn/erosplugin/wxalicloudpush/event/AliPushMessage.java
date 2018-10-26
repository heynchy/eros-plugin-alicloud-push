package com.heyn.erosplugin.wxalicloudpush.event;

import java.io.Serializable;
import java.util.Map;

/**
 * Author: heynchy
 * Date:   2018/9/27
 * <p>
 * Introduce:
 */
public class AliPushMessage implements Serializable {
    private String title;                 // 标题(消息，通知都有)
    private String summary;               // 简介（消息的内容，通知的简介）
    private String extraStr;              // 其他参数
    private Map<String, String> extraMap; // 其他参数
    private int action;                   // 推送的操作
    private String messageId;             // 消息/通知的ID

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getExtraStr() {
        return extraStr;
    }

    public void setExtraStr(String extraStr) {
        this.extraStr = extraStr;
    }

    public Map<String, String> getExtraMap() {
        return extraMap;
    }

    public void setExtraMap(Map<String, String> extraMap) {
        this.extraMap = extraMap;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * Builder 的内部类
     */
    public static class Bulider {
        private AliPushMessage pushMessage = new AliPushMessage();

        public Bulider setTitle(String title) {
            pushMessage.setTitle(title);
            return this;
        }

        public Bulider setSummary(String summary) {
            pushMessage.setSummary(summary);
            return this;
        }


        public Bulider setExtraStr(String extraStr) {
            pushMessage.setExtraStr(extraStr);
            return this;
        }


        public Bulider setExtraMap(Map extraMap) {
            pushMessage.setExtraMap(extraMap);
            return this;
        }


        public Bulider setAction(int action) {
            pushMessage.setAction(action);
            return this;
        }


        public Bulider setMessageId(String messageId) {
            pushMessage.setMessageId(messageId);
            return this;
        }


        public AliPushMessage create() {
            return pushMessage;
        }
    }
}
