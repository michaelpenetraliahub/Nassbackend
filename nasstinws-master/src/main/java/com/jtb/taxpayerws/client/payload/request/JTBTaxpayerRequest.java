package com.jtb.taxpayerws.client.payload.request;

import java.io.Serializable;

public class JTBTaxpayerRequest implements Serializable {
    private String tin;

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }
}