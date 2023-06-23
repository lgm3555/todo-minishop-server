package com.mini.shop.auth.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tMember")
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone", length = 100)
    private String phone;

    @Column(name = "address", length = 200)
    private String address;

    @ManyToMany
    @JoinTable(
            name = "tMemberRole",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    private Set<Role> roles;

    @Column(name = "refresh_token")
    private String refreshToken;

    public void updateRefreshToken(String newToken) {
        this.refreshToken = newToken;
    }
}