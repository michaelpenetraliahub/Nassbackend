package com.jtb.taxpayerws.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReportRequest {
    private String keyword;
    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    private String from;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    private String to;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
