package com.jtb.taxpayerws.dto;

import com.jtb.taxpayerws.constants.AppConstants;

public class EmailDto {
    private String from = AppConstants.SENDER_CUSTOM_EMAIL;
    private String alias = AppConstants.SENDER_CUSTOM_NAME;
    private String to;
    private String subject;
    private String message;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
