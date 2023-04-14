package com.jtb.taxpayerws.utils;

public interface EmailNotificationProxy {
    void sendMail(String from, String alias, String to, String subject, String message);
}
