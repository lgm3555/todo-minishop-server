package com.mini.shop.auth.service;

import com.mini.shop.auth.dto.UserDto;
import com.mini.shop.auth.entity.Member;
import com.mini.shop.auth.entity.Role;
import com.mini.shop.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDto signUp(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("아이디가 이미 존재합니다.");
        }

        Role role = Role.builder()
                .roleName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .roles(Collections.singletonList(role))
                .build();

        return userDto.convertToDto(userRepository.save(member));
    }
}
