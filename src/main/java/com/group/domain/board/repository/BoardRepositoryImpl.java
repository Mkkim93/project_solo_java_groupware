package com.group.domain.board.repository;

import com.group.application.board.dto.*;
import com.group.domain.board.entity.*;

import com.querydsl.core.BooleanBuilder;
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

    private BooleanExpression checkedKeyword(String searchKeyword) {
        return board.boardTitle.containsIgnoreCase(searchKeyword);
    }

    @Override
    public Page<BoardDTO> findAllByBoard(Pageable pageable) {
        List<BoardDTO> results = jpaQueryFactory
                .select(new QBoardDTO(
                        board.id, board.boardTitle, board.boardContent,
                        employee.empName,
                        board.boardRegDate, board.boardViewCount, board.boardIsDeleted))
                .from(board)
                .innerJoin(board.employee, employee)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(board.count())
                .from(board)
                .innerJoin(board.employee, employee);
        return PageableExecutionUtils.getPage(results, pageable, () -> count.fetchCount());
    }

    @Override
    public BoardDTO findByOneBoard(Integer id) {
       return jpaQueryFactory
                .select(new QBoardDTO(
                        board.id, board.boardTitle, board.boardContent,
                        employee.empName,
                        board.boardRegDate, board.boardViewCount, board.boardIsDeleted))
                .from(board)
                .innerJoin(board.employee, employee)
                .where(board.id.eq(id))
                .fetchOne();
    }

    @Override
    public Page<FreeBoardDTO> findAllByFreeBoard(String searchKeyword, Pageable pageable) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(board.boardIsDeleted.eq("N"));

        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            booleanBuilder.and(checkedKeyword(searchKeyword));
        }

        List<FreeBoardDTO> results = jpaQueryFactory
                .select(new QFreeBoardDTO(
                        freeBoard.id, freeBoard.board.id,
                        board.boardTitle,
                        employee.empName,
                        board.boardContent, board.boardRegDate,
                        board.boardViewCount, board.boardIsDeleted))
                .from(freeBoard)
                .innerJoin(freeBoard.board, board)
                .innerJoin(board.employee, employee)
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(freeBoard.count())
                .from(freeBoard)
                .innerJoin(freeBoard.board, board)
                .innerJoin(board.employee, employee)
                .where(booleanBuilder);
        return PageableExecutionUtils.getPage(results, pageable, () -> count.fetchCount());
    }



    @Override
    public FreeBoardDTO findByOneFreeBoard(Integer id) {
        return jpaQueryFactory.select(new QFreeBoardDTO(
                        freeBoard.id, freeBoard.board.id,
                        board.boardTitle,
                        employee.empName,
                        board.boardContent, board.boardRegDate,
                        board.boardViewCount, board.boardIsDeleted))
                .from(freeBoard)
                .innerJoin(freeBoard.board, board)
                .innerJoin(board.employee, employee)
                .where(freeBoard.id.eq(id))
                .fetchOne();
    }

    @Override
    public Page<NoticeBoardDTO> findAllByNoticeBoard(String searchKeyword, Pageable pageable) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(board.boardIsDeleted.eq("N"));

        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            booleanBuilder.and(checkedKeyword(searchKeyword));
        }

        List<NoticeBoardDTO> results = jpaQueryFactory
                .select(new QNoticeBoardDTO(
                        noticeBoard.id, noticeBoard.board.id,
                        board.boardTitle, board.boardContent,
                        employee.empName, board.boardRegDate, board.boardViewCount,
                        board.boardIsDeleted))
                .from(noticeBoard)
                .innerJoin(noticeBoard.board, board)
                .innerJoin(board.employee, employee)
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(noticeBoard.count())
                .from(noticeBoard)
                .innerJoin(noticeBoard.board, board)
                .innerJoin(board.employee, employee)
                .where(booleanBuilder);
        return PageableExecutionUtils.getPage(results, pageable, () -> count.fetchCount());
    }

    @Override
    public NoticeBoardDTO findByOneNoticeBoard(Integer id) {
        return jpaQueryFactory.select(new QNoticeBoardDTO(
                        noticeBoard.id, noticeBoard.board.id,
                        board.boardTitle,
                        employee.empName,
                        board.boardContent,
                        board.boardRegDate,
                        board.boardViewCount, board.boardIsDeleted))
                .from(noticeBoard)
                .innerJoin(noticeBoard.board, board)
                .innerJoin(board.employee, employee)
                .where(noticeBoard.id.eq(id))
                .fetchOne();
    }

    @Override
    public Page<FileBoardDTO> findAllByFileBoard(String searchKeyword, Pageable pageable) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(board.boardIsDeleted.eq("N"));

        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            booleanBuilder.and(checkedKeyword(searchKeyword));
        }

        List<FileBoardDTO> results = jpaQueryFactory
                .select(new QFileBoardDTO(
                        fileBoard.id, fileBoard.board.id,
                        board.boardTitle, board.boardContent,
                        employee.empName,
                        board.boardRegDate, board.boardViewCount, board.boardIsDeleted))
                .from(fileBoard)
                .innerJoin(fileBoard.board, board)
                .innerJoin(board.employee, employee)
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(fileBoard.count())
                .from(fileBoard)
                .innerJoin(fileBoard.board, board)
                .innerJoin(board.employee, employee)
                .where(booleanBuilder);
        return PageableExecutionUtils.getPage(results, pageable, () -> count.fetchCount());
    }

    /**
     * 하나의 비즈니스 로직에서 두개의 쿼리가 실행된다
     * 한방쿼리로 해결할 수 잇었지만 dto 가 무거워 지는 것 같음..
     * 일단 진행 나중에 리팩토링할 때 다시 재검토
     */
    @Override
    public FileBoardDTO findByOneFileBoard(Integer id) {
        return jpaQueryFactory
                .select(new QFileBoardDTO(
                        fileBoard.id, fileBoard.board.id,
                        board.boardTitle, board.boardContent,
                        employee.empName,
                        board.boardRegDate, board.boardViewCount, board.boardIsDeleted))
                .from(fileBoard)
                .innerJoin(fileBoard.board, board)
                .innerJoin(board.employee, employee)
                .where(fileBoard.id.eq(id))
                .fetchOne();
    }

    @Override
    public Page<QnABoardDTO> findAllByQnABoard(String searchKeyword, Pageable pageable) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(board.boardIsDeleted.eq("N"));

        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            booleanBuilder.and(checkedKeyword(searchKeyword));
        }

        List<QnABoardDTO> results = jpaQueryFactory
                .select(new QQnABoardDTO(
                        qnABoard.id, qnABoard.board.id,
                        board.boardTitle, board.boardContent,
                        employee.empName,
                        qnABoard.boardPass,
                        board.boardRegDate, board.boardViewCount, board.boardIsDeleted,
                        qnABoard.boardSecret
                )).from(qnABoard)
                .innerJoin(qnABoard.board, board)
                .innerJoin(board.employee, employee)
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory.select(qnABoard.count())
                .from(qnABoard)
                .innerJoin(qnABoard.board, board)
                .innerJoin(board.employee, employee)
                .where(booleanBuilder)
                .fetchOne();
        return new PageImpl<>(results, pageable, count);
    }

    public QnABoardDTO findByOneBuilder(QnABoardDTO dto) {
        if (dto.getBoardPass() == null) {
            return findByOneQnABoardNotPass(dto.getId());
        }
        return findByOneQnABoard(dto.getId(), dto.getBoardPass());
    }

    public QnABoardDTO findByOneBuilder(Integer id, String boardPass) {
        if (boardPass == null || boardPass.isEmpty()) {
            return findByOneQnABoardNotPass(id);
        }
        return findByOneQnABoard(id, boardPass);
    }

    @Override
    public QnABoardDTO findByOneQnABoard(Integer id, String boardPass) {

        return jpaQueryFactory
                .select(new QQnABoardDTO(
                        qnABoard.id, qnABoard.board.id,
                        board.boardTitle, board.boardContent,
                        employee.empName,
                        qnABoard.boardPass,
                        board.boardRegDate, board.boardViewCount, board.boardIsDeleted,
                        qnABoard.boardSecret))
                .from(qnABoard)
                .innerJoin(qnABoard.board, board)
                .innerJoin(board.employee, employee)
                .where(
                        qnABoard.boardSecret.eq("Y"),
                        qnABoard.id.eq(id),
                        qnABoard.boardPass.eq(boardPass)
                ).fetchOne();
    }

    @Override
    public QnABoardDTO findByOneQnABoardNotPass(Integer id) {
        return jpaQueryFactory.select(new QQnABoardDTO(
                        qnABoard.id, qnABoard.board.id,
                        board.boardTitle, board.boardContent,
                        employee.empName,
                        qnABoard.boardPass,
                        board.boardRegDate, board.boardViewCount, board.boardIsDeleted,
                        qnABoard.boardSecret))
                .from(qnABoard)
                .innerJoin(qnABoard.board, board)
                .innerJoin(board.employee, employee)
                .where(qnABoard.id.eq(id))
                .fetchOne();
    }
}
