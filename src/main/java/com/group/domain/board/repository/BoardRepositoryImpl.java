package com.group.domain.board.repository;

import com.group.application.board.dto.*;
import com.group.domain.board.entity.*;
import com.group.web.board.controller.BoardController;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.apache.ibatis.ognl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.group.domain.board.entity.QBoard.*;
import static com.group.domain.board.entity.QFileBoard.*;
import static com.group.domain.board.entity.QFreeBoard.*;
import static com.group.domain.board.entity.QNoticeBoard.*;
import static com.group.domain.board.entity.QQnABoard.*;
import static com.group.domain.hr.entity.QEmployee.*;

@Repository
@Transactional
public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    public BoardRepositoryImpl(EntityManager entityManager) {
        super(Board.class);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public QnABoardDTO findByIdQnABoard(Integer id, Integer qBoardPass) {
       return jpaQueryFactory
                .select(new QQnABoardDTO(
                        board.id,
                        board.boardTitle,
                        board.boardContent,
                        employee.empName,
                        qnABoard.qBoardPass,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted,
                        qnABoard.qBoardIsSecret
                )).from(qnABoard)
                .leftJoin(qnABoard.boardId, board)
                .leftJoin(board.empId, employee)
                .where(
                        board.id.eq(id),
                        qnABoard.qBoardPass.eq(qBoardPass)
                ).fetchOne();
    }

    @Override
    public NoticeBoardDTO findByIdNoticeBoard(Integer id) {

        return jpaQueryFactory.select(new QNoticeBoardDTO(
                board.id,
                board.boardTitle,
                employee.empName,
                board.boardContent,
                board.boardRegDate,
                board.boardViewCount,
                board.boardIsDeleted
        )).from(noticeBoard)
                .leftJoin(noticeBoard.boardId, board)
                .leftJoin(board.empId, employee)
                .where(board.id.eq(id))
                .fetchOne();
    }

    @Override
    public BoardDTO findByIdBoard(Integer id) {

       return jpaQueryFactory
                .select(new QBoardDTO(
                        board.id,
                        board.boardTitle,
                        board.boardContent,
                        employee.empName,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted))
                .from(board)
               .leftJoin(board.empId, employee)
                .where(board.id.eq(id))
                .fetchOne();
    }

    @Override
    public Page<BoardDTO> findAllByBoard(Pageable pageable) {
        List<BoardDTO> results = jpaQueryFactory
                .select(new QBoardDTO(
                        board.id,
                        board.boardTitle,
                        board.boardContent,
                        employee.empName,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted
                        ))
                .from(board)
                .join(board.empId, employee)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(board.count())
                .from(board)
                .join(board.empId, employee);

        return PageableExecutionUtils.getPage(results, pageable, () -> count.fetchCount());
    }

    @Override
    public Page<FreeBoardDTO> findAllByFreeBoard(Pageable pageable) {

        List<FreeBoardDTO> results = jpaQueryFactory
                .select(new QFreeBoardDTO(
                        board.id,
                        board.boardTitle,
                        employee.empName,
                        board.boardContent,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted
                )).from(freeBoard)
                .leftJoin(freeBoard.boardId, board)
                .leftJoin(board.empId, employee)
                .where(board.boardIsDeleted.eq("N"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(freeBoard.count())
                .from(freeBoard)
                .leftJoin(freeBoard.boardId, board)
                .leftJoin(board.empId, employee);

        return PageableExecutionUtils.getPage(results, pageable, () -> count.fetchCount());
    }

    @Override
    public Page<NoticeBoardDTO> findAllByNoticeBoard(Pageable pageable) {

        List<NoticeBoardDTO> results = jpaQueryFactory
                .select(new QNoticeBoardDTO(
                        board.id,
                        board.boardTitle,
                        board.boardContent,
                        employee.empName,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted
                ))
                .from(noticeBoard)
                .leftJoin(noticeBoard.boardId, board)
                .leftJoin(board.empId, employee)
                .where(board.boardIsDeleted.eq("N"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(noticeBoard.count())
                .from(noticeBoard)
                .leftJoin(noticeBoard.boardId, board)
                .leftJoin(board.empId, employee);

        return PageableExecutionUtils.getPage(results, pageable, () -> count.fetchCount());
    }

    @Override
    public Page<FileBoardDTO> findAllByFileBoard(Pageable pageable) {

        List<FileBoardDTO> results = jpaQueryFactory
                .select(new QFileBoardDTO(
                        board.id,
                        board.boardTitle,
                        board.boardContent,
                        employee.empName,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted,
                        fileBoard.fBoardName,
                        fileBoard.fBoardSize,
                        fileBoard.fBoardType,
                        fileBoard.fBoardPath
                )).from(fileBoard)
                .leftJoin(fileBoard.boardId, board)
                .leftJoin(board.empId, employee)
                .where(board.boardIsDeleted.eq("N"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(fileBoard.count())
                .from(fileBoard)
                .leftJoin(fileBoard.boardId, board)
                .leftJoin(board.empId, employee);

        return PageableExecutionUtils.getPage(results, pageable, () -> count.fetchCount());
    }

    @Override
    public Page<QnABoardDTO> findAllByQnABoard(Pageable pageable) {

        List<QnABoardDTO> results = jpaQueryFactory
                .select(new QQnABoardDTO(
                        board.id,
                        board.boardTitle,
                        board.boardContent,
                        employee.empName,
                        qnABoard.qBoardPass,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted,
                        qnABoard.qBoardIsSecret
                )).from(qnABoard)
                .leftJoin(qnABoard.boardId, board)
                .leftJoin(board.empId, employee)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(board.boardIsDeleted.eq("N"))
                .fetch();

        Long count = jpaQueryFactory.select(qnABoard.count())
                .from(qnABoard)
                .leftJoin(qnABoard.boardId, board)
                .leftJoin(board.empId, employee)
                .fetchOne();

        return new PageImpl<>(results, pageable, count);
    }

    @Override
    public FreeBoardDTO findByIdFreeBoard(Integer id) {

        FreeBoardDTO freeBoardDTO = jpaQueryFactory.select(new QFreeBoardDTO(
                        board.id,
                        board.boardTitle,
                        employee.empName,
                        board.boardContent,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted
                )).from(freeBoard)
                .join(freeBoard.boardId, board)
                .join(board.empId, employee)
                .where(board.id.eq(id))
                .fetchOne();

        return freeBoardDTO;
    }

    /**
     * 하나의 비즈니스 로직에서 두개의 쿼리가 실행된다
     * 한방쿼리로 해결할 수 잇었지만 dto 가 무거워 지는 것 같음..
     * 일단 진행 나중에 리팩토링할 때 다시 재검토
     */
    @Override
    public FileBoardDTO findByIdFileBoard(Integer id) {

        return jpaQueryFactory
                .select(new QFileBoardDTO(
                        board.id,
                        board.boardTitle,
                        board.boardContent,
                        employee.empName,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted,
                        fileBoard.fBoardName,
                        fileBoard.fBoardSize,
                        fileBoard.fBoardType,
                        fileBoard.fBoardPath
                )).from(fileBoard)
                .join(fileBoard.boardId, board)
                .join(board.empId, employee)
                .where(board.id.eq(id))
                .fetchOne();
    }
}
