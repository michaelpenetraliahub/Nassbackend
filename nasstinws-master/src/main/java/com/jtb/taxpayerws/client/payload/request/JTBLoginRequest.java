package com.jtb.taxpayerws.client.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class JTBLoginRequest implements Serializable {

    private String email;

    private String password;

    @JsonProperty(value = "clientname")
    private String clientName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
