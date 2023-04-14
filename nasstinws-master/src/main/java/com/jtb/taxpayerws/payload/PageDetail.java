package com.jtb.taxpayerws.payload;

import java.util.List;

public class PageDetail<T> {
    private Paging paging;
    private List<T> requests;

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public List<T> getRequests() {
        return requests;
    }

    public void setRequests(List<T> requests) {
        this.requests = requests;
    }
}
