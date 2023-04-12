package com.study.basicboard.repository;

import com.study.basicboard.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    void deleteByUserLoginIdAndBoardId(String loginId, Long boardId);
    Boolean existsByUserLoginIdAndBoardId(String loginId, Long boardId);
}
