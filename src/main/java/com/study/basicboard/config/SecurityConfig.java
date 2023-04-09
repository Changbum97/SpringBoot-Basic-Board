package com.study.basicboard.config;

import com.study.basicboard.config.auth.LoginSuccessHandler;
import com.study.basicboard.config.auth.UserDetail;
import com.study.basicboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/users/login")      // 로그인 페이지
                .usernameParameter("loginId")   // 로그인에 사용될 id
                .passwordParameter("password")  // 로그인에 사용될 password
                .failureUrl("/users/login?fail")    // 로그인 실패 시 redirect 될 URL
                .successHandler(new LoginSuccessHandler(userRepository))
                .and()
                .logout()
                .logoutUrl("/users/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .build();
    }
}
