package com.samin.dosan.domain.board;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.web.param.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static com.samin.dosan.domain.board.QBoard.board;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final JPAQueryFactory queryFactory;

    public List<Board> findAll(SearchParam searchParam) {
        BooleanBuilder builder = new BooleanBuilder();
        String searchWorld = searchParam.getSearchWorld();

        if (searchWorld != null) {
            builder.and(board.title.contains(searchWorld));
        }

        return queryFactory.selectFrom(board)
                .where(builder)
                .fetch();
    }

    public Board findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));
    }

    @Transactional
    public void updateBoard(Board board) {
        Board findBoard = boardRepository.findById(board.getId()).
                orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));

        findBoard.updateBoard(board);
    }

    @Transactional
    public Long save(Board board) {
        return boardRepository.save(board).getId();
    }
}
