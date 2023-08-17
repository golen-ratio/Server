package com.umc.goldenratio.config.security;

import com.umc.goldenratio.api.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsersService usersService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeRequests() // 요청에 의한 보안 검사
                .antMatchers("/join", "/login","/v2/api-docs", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**","/check/**","/reissue").permitAll()
                .antMatchers("/golden-ratio/**").authenticated() // golden-ratio 이하는 권한이 있는이에게만 요청된다
                .anyRequest().authenticated() //어떤 요청에도 보안 검사
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtFilter(usersService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}