package com.tst.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Value("${custom.front}")
    private String frontUrl;

    @Value("${custom.front2}")
    private String frontUrl2;

    @Value("${custom.front3}")
    private String frontUrl3;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // 허용할 Origin (Postman 테스트도 고려하여 null 포함)
        config.setAllowedOrigins(List.of(frontUrl,frontUrl2,frontUrl3));


        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(Customizer.withDefaults())            // 등록된 CorsConfigurationSource를 사용
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/**").permitAll()     // 모든 경로 허용
//                        .anyRequest().authenticated()
//                );
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                //.cors(Customizer.withDefaults())
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 💡 CORS 설정 명시
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()     // 프리플라이트 허용
                        .requestMatchers("/tests/**").permitAll()                   // ✅ 먼저 공개 경로 지정
                        .requestMatchers(HttpMethod.GET, "/**").authenticated()     // 그 외 GET은 인증 필요
                        .requestMatchers("/**").authenticated()                     // 그 외 모두 인증 필요
                );
        return http.build();
    }


}
