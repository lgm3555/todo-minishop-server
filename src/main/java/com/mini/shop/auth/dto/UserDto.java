package com.mini.shop.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mini.shop.auth.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String name;

    private String email;

    private String phone;

    private String address;

    public static UserDto convertToDto(Member member) {
        UserDto userDto = new UserDto();
        userDto.setId(member.getId());
        userDto.setName(member.getName());
        userDto.setEmail(member.getEmail());
        userDto.setPhone(member.getPhone());
        userDto.setAddress(member.getAddress());

        return userDto;
    }
}
