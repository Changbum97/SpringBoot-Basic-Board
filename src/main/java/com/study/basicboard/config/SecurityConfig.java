package com.study.basicboard.config;

import com.study.basicboard.config.auth.MyAccessDeniedHandler;
import com.study.basicboard.config.auth.MyAuthenticationEntryPoint;
import com.study.basicboard.config.auth.MyLoginSuccessHandler;
import com.study.basicboard.config.auth.MyLogoutSuccessHandler;
import com.study.basicboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    // 로그인하지 않은 유저들만 접근 가능한 URL
    private static final String[] anonymousUserUrl = {"/users/login", "/users/join"};

    // 로그인한 유저들만 접근 가능한 URL
    private static final String[] authenticatedUserUrl = {"/boards/**/**/edit", "/boards/**/**/delete", "/likes/**", "/users/myPage/**", "/users/edit", "/users/delete"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers(anonymousUserUrl).anonymous()
                .antMatchers(authenticatedUserUrl).authenticated()
                .antMatchers("/boards/greeting/write").hasAnyAuthority("BRONZE", "ADMIN")
                .antMatchers(HttpMethod.POST, "/boards/greeting").hasAnyAuthority("BRONZE", "ADMIN")
                .antMatchers("/boards/free/write").hasAnyAuthority("SILVER", "GOLD", "ADMIN")
                .antMatchers(HttpMethod.POST, "/boards/free").hasAnyAuthority("SILVER", "GOLD", "ADMIN")
                .antMatchers("/boards/gold/**").hasAnyAuthority("GOLD", "ADMIN")
                .antMatchers("/users/admin/**").hasAuthority("ADMIN")
                .antMatchers("/comments/**").hasAnyAuthority("BRONZE", "SILVER", "GOLD", "ADMIN")
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler(userRepository))           // 인가 실패
                .authenticationEntryPoint(new MyAuthenticationEntryPoint()) // 인증 실패
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
