package com.mini.shop.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mini.shop.auth.controller.AuthTokenController;
import com.mini.shop.auth.dto.TokenDto;
import com.mini.shop.auth.entity.Member;
import com.mini.shop.auth.entity.Role;
import com.mini.shop.auth.repository.UserRepository;
import com.mini.shop.config.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mini.shop.config.jwt.JwtConstants.*;

@Transactional
@Service
public class AuthTokenService {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenController.class);

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthTokenService(UserRepository userRepository, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userRepository = userRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    public Optional<TokenDto> refresh(String refreshToken) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET)).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);

        // === Access Token 재발급 === //
        long now = System.currentTimeMillis();
        String username = decodedJWT.getSubject();
        Member member = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        if (!member.getRefreshToken().equals(refreshToken)) {
            throw new JWTVerificationException("유효하지 않은 Refresh Token 입니다.");
        }

        String accessToken = JWT.create()
                .withSubject(member.getUsername())
                .withExpiresAt(new Date(now + AT_EXP_TIME))
                .withClaim("roles", member.getRoles().stream().map(Role::getRoleName)
                        .collect(Collectors.toList()))
                .sign(Algorithm.HMAC256(JWT_SECRET));

        TokenDto tokenDto = new TokenDto();

        // === 현재시간과 Refresh Token 만료날짜를 통해 남은 만료기간 계산 === //
        // === Refresh Token 만료시간 계산해 1개월 미만일 시 refresh token도 발급 === //
        long refreshExpireTime = decodedJWT.getClaim("exp").asLong() * 1000;
        long diffDays = (refreshExpireTime - now) / 1000 / (24 * 3600);
        long diffMin = (refreshExpireTime - now) / 1000 / 60;
        if (diffMin < 5) {
            String newRefreshToken = JWT.create()
                    .withSubject(member.getUsername())
                    .withExpiresAt(new Date(now + RT_EXP_TIME))
                    .sign(Algorithm.HMAC256(JWT_SECRET));
            tokenDto.setRefreshToken(newRefreshToken);
            member.updateRefreshToken(newRefreshToken);
        }

        tokenDto.setAccessToken(accessToken);

        return Optional.of(tokenDto);
    }

    public void updateRefreshToken(String username, String refreshToken) {
        Member member = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        member.updateRefreshToken(refreshToken);
    }
}
