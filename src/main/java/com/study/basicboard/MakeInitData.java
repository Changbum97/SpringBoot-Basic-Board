package com.study.basicboard;

import com.study.basicboard.domain.entity.Board;
import com.study.basicboard.domain.enum_class.BoardCategory;
import com.study.basicboard.domain.enum_class.UserRole;
import com.study.basicboard.domain.entity.User;
import com.study.basicboard.repository.BoardRepository;
import com.study.basicboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MakeInitData {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BCryptPasswordEncoder encoder;

    @PostConstruct
    public void make() {
        User admin = User.builder()
                .loginId("admin")
                .password(encoder.encode("1234"))
                .createdAt(LocalDateTime.now())
                .nickname("관리자")
                .receivedLikeCnt(0)
                .userRole(UserRole.ADMIN)
                .build();

        userRepository.save(admin);
/*
        User user = User.builder()
                .loginId("user")
                .password(encoder.encode("1234"))
                .createdAt(LocalDateTime.now())
                .nickname("유저1")
                .receivedLikeCnt(0)
                .userRole(UserRole.SILVER)
                .build();

        userRepository.save(user);

        for (int i = 1 ; i <= 60 ; i ++) {
            userRepository.save(User.builder()
                    .loginId("user" + i)
                    .password(encoder.encode("1234"))
                    .createdAt(LocalDateTime.now())
                    .nickname("유저" + i)
                    .receivedLikeCnt(0)
                    .userRole(UserRole.BRONZE)
                    .build());
        }

        for (int i = 1 ; i <= 101 ; i ++) {
            boardRepository.save(Board.builder()
                    .title("제목" + i)
                    .body("내용" + i + i + i)
                    .category(BoardCategory.FREE)
                    .commentCnt(0)
                    .likeCnt(0)
                    .user(user)
                    .build());
        }


        for (int i = 1 ; i <= 12 ; i ++) {
            boardRepository.save(Board.builder()
                    .title("title" + i)
                    .body("내용" + i + i + i)
                    .category(BoardCategory.FREE)
                    .user(admin)
                    .commentCnt(0)
                    .likeCnt(0)
                    .build());
        }*/
    }
}
