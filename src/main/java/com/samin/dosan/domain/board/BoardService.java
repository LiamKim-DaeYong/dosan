//package com.samin.dosan.domain.board;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.samin.dosan.web.back.board.dto.BoardDto;
//import com.samin.dosan.web.back.board.dto.QBoardDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class BoardService {
//
//    private final BoardRepository boardRepository;
//    private final JPAQueryFactory queryFactory;
//    private QBoard board = QBoard.board;
//
//    public List<BoardDto> findAll(Pageable pageable) {
//        return queryFactory.select(new QBoardDto(
//                this.board.id,
//                this.board.title,
//                this.board.createdBy,
//                this.board.createdAt,
//                this.board.commentsList.size(),
//                this.board.content))
//                .from(board)
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//    }
//
//    public Board findById(Long id) {
//        return boardRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));
//    }
//
//    @Transactional
//    public void updateBoard(Board board) {
//        Board findBoard = boardRepository.findById(board.getId()).
//                orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));
//
//        findBoard.updateBoard(board);
//    }
//
//    @Transactional
//    public Long saveBoard(Board board) {
//        return boardRepository.save(board).getId();
//    }
//}
