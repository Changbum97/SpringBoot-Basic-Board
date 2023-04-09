package com.study.basicboard.config.auth;

import com.study.basicboard.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@AllArgsConstructor
public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {

    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 세션 유지 시간 = 3600초
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600);

        // 성공 시 메세지 출력 후 홈 화면으로 redirect
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String loginUserNickname = userRepository.findByLoginId(authentication.getName()).get().getNickname();
        pw.println("<script>alert('" + loginUserNickname+ "님 반갑습니다!'); location.href='/';</script>");
        pw.flush();
    }
}
