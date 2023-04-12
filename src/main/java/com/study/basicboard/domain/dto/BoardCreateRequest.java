package com.study.basicboard.domain.dto;

import com.study.basicboard.domain.entity.Board;
import com.study.basicboard.domain.entity.User;
import com.study.basicboard.domain.enum_class.BoardCategory;
import lombok.Data;

@Data
public class BoardCreateRequest {

    private String title;
    private String body;

    public Board toEntity(BoardCategory category, User user) {
        return Board.builder()
                .user(user)
                .category(category)
                .title(title)
                .body(body)
                .build();
    }
}
