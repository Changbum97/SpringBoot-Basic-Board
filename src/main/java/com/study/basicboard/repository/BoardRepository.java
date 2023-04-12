package com.study.basicboard.repository;

import com.study.basicboard.domain.entity.Board;
import com.study.basicboard.domain.enum_class.BoardCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAllByCategory(BoardCategory category, PageRequest pageRequest);
    Page<Board> findAllByCategoryAndTitleContains(BoardCategory category, String title, PageRequest pageRequest);
    Page<Board> findAllByCategoryAndUserNicknameContains(BoardCategory category, String nickname, PageRequest pageRequest);
}
