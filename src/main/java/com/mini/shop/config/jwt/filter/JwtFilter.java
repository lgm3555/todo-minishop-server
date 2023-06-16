package com.mini.shop.config.jwt.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.shop.config.jwt.TokenProvider;
import com.mini.shop.error.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.mini.shop.config.jwt.JwtConstants.JWT_SECRET;
import static com.mini.shop.config.jwt.JwtConstants.TOKEN_HEADER_PREFIX;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// Token의 인증정보를 SecurityContext의 저장하는 역할 수행
@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        String authrizationHeader = request.getHeader(AUTHORIZATION);

        // 로그인, 리프레시 요청이라면 토큰 검사하지 않음
        if (servletPath.equals("/auth/sign-up") || servletPath.equals("/auth/sign-in") || servletPath.equals("/auth/refresh") || servletPath.equals("/auth/find-pwd")) {
            filterChain.doFilter(request, response);
        } else if (authrizationHeader == null || !authrizationHeader.startsWith(TOKEN_HEADER_PREFIX)) {
            // 인증 확인 엔드포인드가 아니면서 토큰값이 없거나 정상적이지 않다면 401 오류
            if (servletPath.equals("/auth/user")) {
                new ObjectMapper().writeValue(response.getWriter(), "");
            } else {
                logger.info("CustomAuthorizationFilter : JWT Token이 존재하지 않습니다.");
                response.setStatus(SC_UNAUTHORIZED);
                response.setContentType(APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("utf-8");
                ErrorResponse errorResponse = new ErrorResponse("E07", "JWT Token이 존재하지 않습니다.");
                new ObjectMapper().writeValue(response.getWriter(), errorResponse);
            }
        } else {
            try {
                // Access Token만 꺼내옴
                String accessToken = authrizationHeader.substring(TOKEN_HEADER_PREFIX.length());

                // Access Token 검증
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET)).build();
                DecodedJWT decodedJWT = verifier.verify(accessToken);

                // === Access Token 내 Claim에서 Authorities 꺼내 Authentication 객체 생성 & SecurityContext에 저장 === //
                List<String> strAuthorities = decodedJWT.getClaim("roles").asList(String.class);
                List<SimpleGrantedAuthority> authorities = strAuthorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                String id = decodedJWT.getSubject();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                request.setAttribute("id", id);

                filterChain.doFilter(request, response);
            } catch (Exception e) {
                logger.info("JWT 토큰이 잘못되었습니다. message : {}", e.getMessage());
                response.setStatus(SC_UNAUTHORIZED);
                response.setContentType(APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("utf-8");
                ErrorResponse errorResponse = new ErrorResponse("E08", "잘못된 JWT Token 입니다.");
                new ObjectMapper().writeValue(response.getWriter(), errorResponse);
            }
        }
    }
}
