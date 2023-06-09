package com.mini.shop.auth.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Role")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "role_name", length = 50)
    private String roleName;
}
