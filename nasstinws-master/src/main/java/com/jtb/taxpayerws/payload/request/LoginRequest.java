package com.jtb.taxpayerws.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @Email
    private String email;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    private String password;

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
}
