package com.jtb.taxpayerws.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserRequest {
    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    private String firstname;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    private String lastname;

    private String middlename;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    private String phone;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @Email
    private String email;

    private List<String> roles;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
