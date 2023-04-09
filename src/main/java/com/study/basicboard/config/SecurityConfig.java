package com.study.basicboard.config;

import com.study.basicboard.config.auth.MyLoginSuccessHandler;
import com.study.basicboard.config.auth.MyLogoutSuccessHandler;
import com.study.basicboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

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
                // 폼 로그인
                .formLogin()
                .loginPage("/users/login")      // 로그인 페이지
                .usernameParameter("loginId")   // 로그인에 사용될 id
                .passwordParameter("password")  // 로그인에 사용될 password
                .failureUrl("/users/login?fail")         // 로그인 실패 시 redirect 될 URL => 실패 메세지 출력
                .successHandler(new MyLoginSuccessHandler(userRepository))    // 로그인 성공 시 실행 될 Handler
                .and()
                // 로그아웃
                .logout()
                .logoutUrl("/users/logout")     // 로그아웃 URL
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                .and()
                .build();
    }
}
