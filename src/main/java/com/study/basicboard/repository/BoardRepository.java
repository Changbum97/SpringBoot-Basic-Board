package com.study.basicboard.repository;

import com.study.basicboard.domain.entity.Board;
import com.study.basicboard.domain.enum_class.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByCategory(BoardCategory category);
}
