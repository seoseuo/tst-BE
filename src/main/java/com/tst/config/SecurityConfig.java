package com.tst.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Value("${custom.front}")
    private String frontUrl;

    // CORS 설정 빈 등록
    @Bean
    public CorsFilter corsFilter() {
        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
        config.addAllowedOrigin(frontUrl); // 프론트엔드 URL 허용
        config.addAllowedMethod("*"); // 모든 HTTP 메소드 허용
        config.addAllowedHeader("*"); // 모든 헤더 허용
        config.setAllowCredentials(true); // 자격증명(Cookie 등) 허용

        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 모든 URL에 대해 CORS 설정 적용
        return new CorsFilter(source);
    }
}
