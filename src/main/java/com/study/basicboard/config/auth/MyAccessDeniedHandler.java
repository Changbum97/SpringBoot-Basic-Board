package com.study.basicboard.config.auth;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String requestURI = request.getRequestURI();

        // 로그인한 유저가 login, join을 시도한 경우
        if (requestURI.contains("/users/login") || requestURI.contains("/users/join")) {
            // 메세지 출력 후 홈으로 redirect
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('이미 로그인 되어있습니다!'); location.href='/';</script>");
            pw.flush();
        }
    }
}
