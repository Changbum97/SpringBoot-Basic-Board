package com.study.basicboard.config.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 메세지 출력 후 홈으로 redirect
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<script>alert('로그인이 필요한 요청입니다!'); location.href='/users/login';</script>");
        pw.flush();
    }
}
