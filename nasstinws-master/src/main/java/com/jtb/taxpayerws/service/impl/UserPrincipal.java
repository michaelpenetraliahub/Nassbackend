package com.jtb.taxpayerws.service.impl;


import com.jtb.taxpayerws.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class UserPrincipal implements UserDetails {
    private final Long id;
    private final String firstname;
    private final String lastname;
    private final String middlename;
    private final String email;
    private final String phone;
    private final String password;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.firstname = userEntity.getFirstname();
        this.lastname = userEntity.getLastname();
        this.middlename = userEntity.getMiddlename();
        this.email = userEntity.getEmail();
        this.phone = userEntity.getPhone();
        this.password = userEntity.getPassword();
        this.enabled = userEntity.isEnabled();
        this.authorities = userEntity.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getMiddlename() == null ? getFirstname() + " " + getLastname() : getFirstname() + " " + getMiddlename() + " " + getLastname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal userPrincipal = (UserPrincipal) o;
        return Objects.equals(this.id, userPrincipal.id);
    }

    public Set<String> getAuthoritiesAsStringList() {
        return this.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }

}
