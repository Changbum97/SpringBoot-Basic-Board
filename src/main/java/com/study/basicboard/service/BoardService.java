package com.study.basicboard.service;

import com.study.basicboard.domain.dto.BoardCreateRequest;
import com.study.basicboard.domain.dto.BoardDto;
import com.study.basicboard.domain.entity.Board;
import com.study.basicboard.domain.entity.User;
import com.study.basicboard.domain.enum_class.BoardCategory;
import com.study.basicboard.repository.BoardRepository;
import com.study.basicboard.repository.UserRepository;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public List<Board> getBoardList(BoardCategory category) {
        return boardRepository.findAllByCategory(category);
    }

    public BoardDto getBoard(Long boardId, String category) {
        Optional<Board> optBoard = boardRepository.findById(boardId);

        // id에 해당하는 게시글이 없거나 카테고리가 일치하지 않으면 null return
        if (optBoard.isEmpty() || !optBoard.get().getCategory().toString().equalsIgnoreCase(category)) {
            return null;
        }

        return BoardDto.of(optBoard.get());
    }

    public Long writeBoard(BoardCreateRequest req, BoardCategory category, String loginId) {
        User loginUser = userRepository.findByLoginId(loginId).get();

        Board savedBoard = boardRepository.save(req.toEntity(category, loginUser));
        return savedBoard.getId();
    }

    @Transactional
    public Long editBoard(Long boardId, String category, BoardDto dto) {
        Optional<Board> optBoard = boardRepository.findById(boardId);

        // id에 해당하는 게시글이 없거나 카테고리가 일치하지 않으면 null return
        if (optBoard.isEmpty() || !optBoard.get().getCategory().toString().equalsIgnoreCase(category)) {
            return null;
        }

        Board board = optBoard.get();
        board.update(dto);

        return board.getId();
    }

    public Long deleteBoard(Long boardId, String category) {
        Optional<Board> optBoard = boardRepository.findById(boardId);

        // id에 해당하는 게시글이 없거나 카테고리가 일치하지 않으면 null return
        if (optBoard.isEmpty() || !optBoard.get().getCategory().toString().equalsIgnoreCase(category)) {
            return null;
        }

        boardRepository.deleteById(boardId);
        return boardId;
    }
}
