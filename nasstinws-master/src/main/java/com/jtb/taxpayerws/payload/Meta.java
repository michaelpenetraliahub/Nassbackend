package com.jtb.taxpayerws.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Meta {
    private String status;
    private String message;
    private String info;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String source;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
