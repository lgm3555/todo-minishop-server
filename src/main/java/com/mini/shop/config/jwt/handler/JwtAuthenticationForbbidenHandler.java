package com.mini.shop.config.jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.shop.error.ErrorResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// 403 Error Handler
@Component
public class JwtAuthenticationForbbidenHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");

        ErrorResponse errorResponse = new ErrorResponse(403, "해당 자원에 대한 접근 권한이 없습니다.");
        new ObjectMapper().writeValue(response.getWriter(), errorResponse);
    }
}

