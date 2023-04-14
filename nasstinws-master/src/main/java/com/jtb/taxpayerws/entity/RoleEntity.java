package com.jtb.taxpayerws.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "role")
public class RoleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    public RoleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleEntity() {
    }

    public RoleEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String roleName) {
        this.name = roleName;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
