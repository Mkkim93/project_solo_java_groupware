package com.group.domain.board.repository;

import com.group.application.board.dto.CommentDTO;
import com.group.domain.board.entity.CommentBoard;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CommentBoardRepository extends JpaRepository<CommentBoard, Integer> {

    @Query("SELECT new com.group.application.board.dto.CommentDTO(c.id, c.comContent, e.empName, c.comRegDate, e.id, b.id) " +
            "FROM Board b, CommentBoard c, Employee e " +
            "WHERE b.id = c.board.id " +
            "and c.employee.id = e.id " +
            "and b.id = :id " +
            "ORDER BY c.id desc")
    Page<CommentDTO> findByAllComment(@Param("id") Integer id, Pageable pageable);

    @Query("select new com.group.application.board.dto.CommentDTO(c.id, c.comContent, e.empName, c.comRegDate, e.id, b.id, q.boardPass)" +
            "from Employee e, Board b, CommentBoard c, QnABoard q " +
            "where e.id = b.employee.id " +
            "and b.id = c.board.id " +
            "and c.board.id = q.board.id " +
            "and c.board.id = :id ")
    Page<CommentDTO> findByQnaComment(@Param("id") Integer id, Pageable pageable);

}
