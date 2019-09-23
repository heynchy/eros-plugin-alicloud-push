package com.heyn.erosplugin.wxalicloudpush.event;

import java.io.Serializable;

/**
 * Author: Heynchy
 * Date:   2019/9/23
 * <p>
 * Introduce:
 */
public class PushEvent implements Serializable {
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
