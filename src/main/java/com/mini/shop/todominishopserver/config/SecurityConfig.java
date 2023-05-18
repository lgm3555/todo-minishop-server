package com.mini.shop.todominishopserver.config;

import com.mini.shop.todominishopserver.jwt.JwtAccessDeniedHandler;
import com.mini.shop.todominishopserver.jwt.JwtAuthenticationEntryPoint;
import com.mini.shop.todominishopserver.jwt.JwtSecurityConfig;
import com.mini.shop.todominishopserver.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 기본적인 웹 보안을 활성화함.
@EnableWebSecurity
// @PreAuthorize 머노테이션을 메소드 단위로 사용하기 위해 작성.
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter { //추가적인 보안 설정을 위해 extends

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(TokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * https://okky.kr/articles/382738
     *
     * 보안을 구성하는 두가지 영역 -> 인증과 권한
     * 인증은 애플리케이션의 작업을 수행할 수 있는 주체
     * 권한은 인증된 주체가 애플리케이션의 동작을 수행할 수 있도록 허락되어있는지를 결정하는 것.
     *
     * 따라서 권한 승인이 필요한 부분으로 접근하려면 인증 과정을 통해 먼저 주체가 증명되어야함.
     *
     * 권한 부여에도 두가지 영역 -> 웹 요청 권한과 메소드 호출 및 도메인 인스턴스에 대한 접근 권한
     *
     * WebSecurity ignoring VS HttpSecurity permitAll
     * WebSecurity ignoring은 Spring Security 필터 체인에서 특정 경로를 무시하도록 설정 / 정적 자원에 대해서는 인증 과정을 거치지 않도록 하기위함.
     * HttpSecurity permitAll 특정 경로에 대해 모든 사용자에게 접근을 허용하는 설정 /
     */

    @Override
    public void configure(WebSecurity web) throws Exception {
        /**
         * h2-console 하위 모든 요청들과 파비콘 관련 요청은 Spring Security 로직을 수행하지 않도록 설정
         */
        web.ignoring()
                .antMatchers(
                        "/h2-console/**",
                        "/favicon.ico"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * HttpServletRequest를 사용하는 요청들에 대한 접근제한 설정
         * /api/hello에 대한 요청은 인증 없이 접근 허용
         * 나머지 요청들에 대해서는 인증을 받아야함.
         */
        //http.authorizeRequests()
        //        .antMatchers("/api/hello").permitAll()
        //        .anyRequest().authenticated();

        /**
         * Token을 사용하기 떄문에 csrf 설정을 막음
         *
         * Exception을 핸들링 하기 위해 클래스 추가
         *
         * h2-console을 위한 설정 추가
         *
         * 세션을 사용하지 않기 때문에 설정을 STATELESS로 설정
         *
         * 로그인 API, 회원가입 API는 토큰이 없는 상태에기 떄문에 permitAll로 지정
         *
         * JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig 클래스도 적용
         */
        http.csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/api/hello").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/signup").permitAll()
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}
