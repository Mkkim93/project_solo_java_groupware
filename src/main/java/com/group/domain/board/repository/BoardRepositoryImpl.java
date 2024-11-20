package com.group.domain.board.repository;

import com.group.application.board.dto.*;
import com.group.domain.board.entity.*;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
                .innerJoin(board.empId, employee)
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
                .innerJoin(board.empId, employee)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(board.count())
                .from(board)
                .innerJoin(board.empId, employee);

        return PageableExecutionUtils.getPage(results, pageable, () -> count.fetchCount());
    }

    @Override
    public Page<FreeBoardDTO> findAllByFreeBoard(Pageable pageable) {

        List<FreeBoardDTO> results = jpaQueryFactory
                .select(new QFreeBoardDTO(
                        freeBoard.id,
                        freeBoard.boardId.id,
                        board.boardTitle,
                        employee.empName,
                        board.boardContent,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted
                )).from(freeBoard)
                .innerJoin(freeBoard.boardId, board)
                .innerJoin(board.empId, employee)
                .where(board.boardIsDeleted.eq("N"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(freeBoard.count())
                .from(freeBoard)
                .innerJoin(freeBoard.boardId, board)
                .innerJoin(board.empId, employee);

        return PageableExecutionUtils.getPage(results, pageable, () -> count.fetchCount());
    }

    @Override
    public FreeBoardDTO findByIdFreeBoard(Integer id) {

        FreeBoardDTO freeBoardDTO = jpaQueryFactory.select(new QFreeBoardDTO(
                        freeBoard.id,
                        freeBoard.boardId.id,
                        board.boardTitle,
                        employee.empName,
                        board.boardContent,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted
                )).from(freeBoard)
                .innerJoin(freeBoard.boardId, board)
                .innerJoin(board.empId, employee)
                .where(freeBoard.id.eq(id))
                .fetchOne();

        return freeBoardDTO;
    }

    @Override
    public Page<NoticeBoardDTO> findAllByNoticeBoard(Pageable pageable) {

        List<NoticeBoardDTO> results = jpaQueryFactory
                .select(new QNoticeBoardDTO(
                        noticeBoard.id,
                        noticeBoard.boardId.id,
                        board.boardTitle,
                        board.boardContent,
                        employee.empName,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted
                ))
                .from(noticeBoard)
                .innerJoin(noticeBoard.boardId, board)
                .innerJoin(board.empId, employee)
                .where(board.boardIsDeleted.eq("N"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(noticeBoard.count())
                .from(noticeBoard)
                .innerJoin(noticeBoard.boardId, board)
                .innerJoin(board.empId, employee);

        return PageableExecutionUtils.getPage(results, pageable, () -> count.fetchCount());
    }

    @Override
    public Page<FileBoardDTO> findAllByFileBoard(Pageable pageable) {

        List<FileBoardDTO> results = jpaQueryFactory
                .select(new QFileBoardDTO(
                        fileBoard.id,
                        fileBoard.boardId.id,
                        board.boardTitle,
                        board.boardContent,
                        employee.empName,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted
                )).from(fileBoard)
                .innerJoin(fileBoard.boardId, board)
                .innerJoin(board.empId, employee)
                .where(board.boardIsDeleted.eq("N"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(fileBoard.count())
                .from(fileBoard)
                .innerJoin(fileBoard.boardId, board)
                .innerJoin(board.empId, employee);

        return PageableExecutionUtils.getPage(results, pageable, () -> count.fetchCount());
    }

    @Override
    public Page<QnABoardDTO> findAllByQnABoard(Pageable pageable) {

        List<QnABoardDTO> results = jpaQueryFactory
                .select(new QQnABoardDTO(
                        qnABoard.id,
                        qnABoard.boardId.id,
                        board.boardTitle,
                        board.boardContent,
                        employee.empName,
                        qnABoard.boardPass,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted,
                        qnABoard.boardSecret
                )).from(qnABoard)
                .innerJoin(qnABoard.boardId, board)
                .innerJoin(board.empId, employee)
                .where(board.boardIsDeleted.eq("N"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory.select(qnABoard.count())
                .from(qnABoard)
                .innerJoin(qnABoard.boardId, board)
                .innerJoin(board.empId, employee)
                .fetchOne();

        return new PageImpl<>(results, pageable, count);
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
                        fileBoard.id,
                        fileBoard.boardId.id,
                        board.boardTitle,
                        board.boardContent,
                        employee.empName,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted
                )).from(fileBoard)
                .innerJoin(fileBoard.boardId, board)
                .innerJoin(board.empId, employee)
                .where(fileBoard.id.eq(id))
                .fetchOne();
    }

    @Override
    public NoticeBoardDTO findByIdNoticeBoard(Integer id) {

        return jpaQueryFactory.select(new QNoticeBoardDTO(
                        noticeBoard.id,
                        noticeBoard.boardId.id,
                        board.boardTitle,
                        employee.empName,
                        board.boardContent,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted
                )).from(noticeBoard)
                .innerJoin(noticeBoard.boardId, board)
                .innerJoin(board.empId, employee)
                .where(noticeBoard.id.eq(id))
                .fetchOne();
    }

    @Override
    public QnABoardDTO findByIdQnABoard(Integer id, String boardPass) {
        return jpaQueryFactory
                .select(new QQnABoardDTO(
                        qnABoard.id,
                        qnABoard.boardId.id,
                        board.boardTitle,
                        board.boardContent,
                        employee.empName,
                        qnABoard.boardPass,
                        board.boardRegDate,
                        board.boardViewCount,
                        board.boardIsDeleted,
                        qnABoard.boardSecret
                )).from(qnABoard)
                .innerJoin(qnABoard.boardId, board)
                .innerJoin(board.empId, employee)
                .where(
                        qnABoard.id.eq(id),
                        (eqBoardPass(boardPass))
                ).fetchOne();
    }

    private BooleanExpression eqBoardPass(String boardPass) {
        if (boardPass == null || boardPass.trim().isEmpty()) {
            return null;
        }
        return qnABoard.boardPass.eq(boardPass);
    }

    @Override
    public QnABoardDTO findByIdQnABoardNotPass(Integer id) {
        return jpaQueryFactory.select(new QQnABoardDTO(
                qnABoard.id,
                qnABoard.boardId.id,
                board.boardTitle,
                board.boardContent,
                employee.empName,
                qnABoard.boardPass,
                board.boardRegDate,
                board.boardViewCount,
                board.boardIsDeleted,
                qnABoard.boardSecret))
                .from(qnABoard)
                .innerJoin(qnABoard.boardId, board)
                .innerJoin(board.empId, employee)
                .where(qnABoard.id.eq(id))
                .fetchOne();
    }
}
