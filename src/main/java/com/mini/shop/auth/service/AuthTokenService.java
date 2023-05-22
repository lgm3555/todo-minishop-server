package com.mini.shop.auth.service;

import com.mini.shop.auth.controller.AuthTokenController;
import com.mini.shop.auth.dto.LoginDto;
import com.mini.shop.auth.dto.TokenDto;
import com.mini.shop.config.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenController.class);

    public static final String TOKEN_HEADER_PREFIX = "Bearer ";

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthTokenService(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    public TokenDto authorize(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccessToken(tokenProvider.createAccessToken(authentication));
        tokenDto.setRefreshToken(tokenProvider.createRefreshToken(authentication));
        return tokenDto;
    }

    public TokenDto refresh(String refreshToken) {
        TokenDto tokenDto = new TokenDto();

        if (tokenProvider.validateToken(refreshToken)) {
            Authentication authentication = tokenProvider.getAuthentication(refreshToken);
            tokenDto.setAccessToken(tokenProvider.createAccessToken(authentication));
        }
        
        return tokenDto;
    }
}
