package com.mini.shop.config.jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.shop.error.ErrorResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// 401 Error Handler
@Component
public class JwtAuthenticationUnauthorizedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");

        ErrorResponse errorResponse = new ErrorResponse(401, "인증되지 않거나, 인증 정보가 없습니다.");
        new ObjectMapper().writeValue(response.getWriter(), errorResponse);
    }
}
