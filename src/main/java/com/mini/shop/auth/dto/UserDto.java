package com.mini.shop.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mini.shop.auth.entity.Member;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Size(min = 3, max = 50)
    private String id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 3, max = 100)
    private String password;

    @Size(min = 3, max = 50)
    private String name;

    @Size(min = 3, max = 100)
    private String email;

    @Size(min = 3, max = 100)
    private String phone;

    @Size(min = 3, max = 200)
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
