package com.mini.shop.auth.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mini.shop.auth.dto.TokenDto;
import com.mini.shop.auth.service.AuthTokenService;
import com.mini.shop.error.exception.ExpireTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.mini.shop.config.jwt.JwtConstants.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/auth")
public class AuthTokenController {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenController.class);

    private final AuthTokenService authTokenService;
    
    public AuthTokenController(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @GetMapping("/user")
    public ResponseEntity<String> userAuthorize() {
        return new ResponseEntity<>("active", HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(HttpServletRequest request, HttpServletResponse response) throws ExpireTokenException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_HEADER_PREFIX)) {
            throw new JWTVerificationException("JWT Token이 존재하지 않습니다.");
        }
        String refreshToken = authorizationHeader.substring(TOKEN_HEADER_PREFIX.length());
        TokenDto tokenDto = authTokenService.refresh(refreshToken).get();
        response.setHeader(AT_HEADER, tokenDto.getAccessToken());
        if (tokenDto.getRefreshToken() != null) {
            response.setHeader(RT_HEADER, tokenDto.getRefreshToken());
        }
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }
}
