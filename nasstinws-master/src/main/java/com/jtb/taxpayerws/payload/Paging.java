package com.jtb.taxpayerws.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Paging {
    private long page;
    @JsonProperty("page_size")
    private long pageSize;

    @JsonProperty("total_pages")
    private long totalPages;

    @JsonProperty("total_record")
    private long totalRecord;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }
}
