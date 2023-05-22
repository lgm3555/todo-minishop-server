package com.mini.shop.auth.controller;

import com.mini.shop.auth.dto.LoginDto;
import com.mini.shop.auth.dto.TokenDto;
import com.mini.shop.auth.service.AuthTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthTokenController {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenController.class);

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_HEADER_PREFIX = "Bearer ";
    private final AuthTokenService authTokenService;

    public AuthTokenController(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {
        TokenDto tokenDto = authTokenService.authorize(loginDto);
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_HEADER_PREFIX)) {
            throw new RuntimeException("JWT Token이 존재하지 않습니다.");
        }
        String refreshToken = authorizationHeader.substring(TOKEN_HEADER_PREFIX.length());
        TokenDto tokenDto = authTokenService.refresh(refreshToken);
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }
}
