package com.mini.shop.auth.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tRole")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "role_name", length = 50)
    private String roleName;
}
