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
                .userRole(UserRole.ADMIN)
                .build();

        userRepository.save(admin);

        User user = User.builder()
                .loginId("user")
                .password(encoder.encode("1234"))
                .createdAt(LocalDateTime.now())
                .nickname("유저1")
                .userRole(UserRole.BRONZE)
                .build();

        userRepository.save(user);

        Board board = Board.builder()
                .title("제목1")
                .body("내용111")
                .category(BoardCategory.FREE)
                .user(user)
                .build();

        boardRepository.save(board);
    }
}
