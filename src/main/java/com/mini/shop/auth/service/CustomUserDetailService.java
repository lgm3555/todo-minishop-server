package com.mini.shop.auth.service;

import com.mini.shop.auth.entity.Member;
import com.mini.shop.auth.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DB에서 USER 정보를 가져와 리턴해줌.
 */
@Component("userDetailService")
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("UserDetailsService - loadUserByUsername : 데이터베이스에서 찾을 수 없습니다."));

        List<SimpleGrantedAuthority> authorities = member.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());

        return new User(member.getId(), member.getPassword(), authorities);
    }
}
