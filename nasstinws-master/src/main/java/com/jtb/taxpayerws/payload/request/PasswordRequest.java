package com.jtb.taxpayerws.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PasswordRequest {
    @JsonProperty("old_password")
    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    private String oldPassword;

    @JsonProperty("new_password")
    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    private String newPassword;

    @JsonProperty("confirm_password")
    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    private String confirmPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
