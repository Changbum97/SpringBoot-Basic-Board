package com.study.basicboard.domain.entity;

import com.study.basicboard.domain.enum_class.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;     // 로그인할 때 사용하는 아이디
    private String password;    // 비밀번호
    private String nickname;    // 닉네임
    private LocalDateTime createdAt;    // 가입 시간

    @Enumerated(EnumType.STRING)
    private UserRole userRole;      // 권한

    @OneToMany(mappedBy = "user")
    private List<Board> boards;     // 작성글

    @OneToMany(mappedBy = "user")
    private List<Like> likes;       // 좋아요

    @OneToMany(mappedBy = "user")
    private List<Comment> comments; // 댓글

    public void rankUp(UserRole userRole, Authentication auth) {
        this.userRole = userRole;

        // 현재 저장되어 있는 Authentication 수정 => 재로그인 하지 않아도 권한 수정 되기 위함
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>();
        updatedAuthorities.add(new SimpleGrantedAuthority(userRole.name()));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
