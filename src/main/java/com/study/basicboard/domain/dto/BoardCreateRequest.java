package com.study.basicboard.domain.dto;

import com.study.basicboard.domain.entity.Board;
import com.study.basicboard.domain.entity.User;
import com.study.basicboard.domain.enum_class.BoardCategory;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardCreateRequest {

    private String title;
    private String body;
    private MultipartFile uploadImage;

    public Board toEntity(BoardCategory category, User user) {
        return Board.builder()
                .user(user)
                .category(category)
                .title(title)
                .body(body)
                .likeCnt(0)
                .commentCnt(0)
                .build();
    }
}
