package com.study.basicboard.service;

import com.study.basicboard.domain.entity.Board;
import com.study.basicboard.domain.enum_class.BoardCategory;
import com.study.basicboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getBoardList(BoardCategory category) {
        return boardRepository.findAllByCategory(category);
    }
}
