package com.study.basicboard.service;

import com.study.basicboard.domain.entity.Like;
import com.study.basicboard.repository.BoardRepository;
import com.study.basicboard.repository.LikeRepository;
import com.study.basicboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public void addLike(String loginId, Long boardId) {
        likeRepository.save(Like.builder()
                        .user(userRepository.findByLoginId(loginId).get())
                        .board(boardRepository.findById(boardId).get())
                        .build());
    }

    @Transactional
    public void deleteLike(String loginId, Long boardId) {
        likeRepository.deleteByUserLoginIdAndBoardId(loginId, boardId);
    }

    public Boolean checkLike(String loginId, Long boardId) {
        return likeRepository.existsByUserLoginIdAndBoardId(loginId, boardId);
    }
}
