package com.jtb.taxpayerws.payload.request;

import com.jtb.taxpayerws.payload.Meta;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ApiRequest<T> {
    @Valid
    @NotNull(message = "{invalid-meta.field}")
    private Meta meta;

    @Valid
    @NotNull(message = "{invalid-data.field}")
    private T data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
