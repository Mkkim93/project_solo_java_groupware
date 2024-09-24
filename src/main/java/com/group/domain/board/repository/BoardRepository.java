package com.group.domain.board.repository;

import com.group.domain.board.entity.Board;
import com.group.domain.board.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
