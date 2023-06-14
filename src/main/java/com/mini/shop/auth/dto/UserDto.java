package com.mini.shop.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mini.shop.auth.entity.Member;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 3, max = 100)
    private String password;

    @Size(min = 3, max = 50)
    private String nickname;

    public static UserDto convertToDto(Member member) {
        UserDto userDto = new UserDto();
        userDto.setUsername(member.getUsername());
        userDto.setPassword(member.getPassword());
        userDto.setNickname(member.getNickname());

        return userDto;
    }
}
