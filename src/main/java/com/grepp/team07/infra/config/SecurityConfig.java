package com.grepp.team07.infra.config;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Value("${remember-me.key}")
    private String rememberMeKey;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

                boolean isAdmin = authentication.getAuthorities()
                    .stream()
                    .anyMatch(authority ->
                        authority.getAuthority().equals("ROLE_ADMIN"));

                if(isAdmin){
                    response.sendRedirect("/admin/orders");
                    return;
                }

                response.sendRedirect("/");
            }
        };

    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return (request, response, exception) -> {
            response.sendRedirect("/member/signin?error=true");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // * : 1depth 아래 모든 경로
        // ** : 모든 depth 의 모든 경로
        // Security Config 에는 인증과 관련된 설정만 지정 (PermitAll or Authenticated)
        http
            .csrf(csrf -> csrf.disable()) // csrf 비활성화
            .authorizeHttpRequests(
                (requests) -> requests
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers(GET, "/", "/assets/**", "/download/**").permitAll()
                    .requestMatchers(GET, "/member/signup", "/member/signin").permitAll()
                    .requestMatchers(POST, "/member/signup", "/member/signin").permitAll()
                    .requestMatchers(GET, "/api/member/exists/*").permitAll()
                    .requestMatchers("/**").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/member/signin")
                .usernameParameter("userId")
                .loginProcessingUrl("/member/signin")
                .defaultSuccessUrl("/")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .permitAll()
            )
            .rememberMe(rememberMe -> rememberMe.key(rememberMeKey))
            .logout(logout -> logout
                .logoutSuccessUrl("/member/signin")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .permitAll()
            );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
