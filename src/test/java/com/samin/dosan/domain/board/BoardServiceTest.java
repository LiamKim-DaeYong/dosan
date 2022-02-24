package com.samin.dosan.domain.board;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.web.param.SearchParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.samin.dosan.domain.board.QBoard.board;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private JPAQueryFactory queryFactory;

    @Mock(answer = Answers.RETURNS_SELF)
    private JPAQuery query;

    @InjectMocks
    private BoardService boardService;

    private Board board1;
    private Board board2;
    private Board board3;
    private SearchParam searchParam;

    @BeforeEach
    void init() {
        searchParam = new SearchParam();
        board1 = Board.builder().id(1L).title("제목1").content("내용1").build();
        board2 = Board.builder().id(2L).title("제목2").content("내용2").build();
        board3 = Board.builder().id(3L).title("제목3").content("내용3").build();
    }

    @Test
    @DisplayName("게시판 조회 테스트")
    void findAll() {
        //given
        when(queryFactory.selectFrom(board)).thenReturn(query);
        when(query.fetch()).thenReturn(Arrays.asList(board1, board2, board3));

        //when
        List<Board> findBoardList = boardService.findAll(searchParam);

        //then
        assertEquals(3, findBoardList.size());
        isEquals(board1, findBoardList.get(0));
        isEquals(board2, findBoardList.get(1));
        isEquals(board3, findBoardList.get(2));
    }

    @Test
    @DisplayName("게시판 검색 테스트")
    void search() {
        //given
        searchParam.setSearchWorld("1");

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(board.title.contains(searchParam.getSearchWorld()));

        when(queryFactory.selectFrom(board)).thenReturn(query);
        when(query.where(builder)).thenReturn(query);
        when(query.fetch()).thenReturn(Arrays.asList(board1, board2, board3).stream().filter(board
                -> board.getTitle().contains(searchParam.getSearchWorld())).collect(Collectors.toList()));

        //when
        List<Board> findBoardList = boardService.findAll(searchParam);

        //then
        assertEquals(1, findBoardList.size());
        isEquals(board1, findBoardList.get(0));

    }

    @Test
    @DisplayName("게시판 저장 테스트")
    void saveTest() {
        when(boardRepository.save(any(Board.class))).thenReturn(board1);
        Long saveId = boardService.save(board1);
        assertEquals(saveId, board1.getId());
    }

    private void isEquals(Board board1, Board board2) {
        assertEquals(board1.getId(), board2.getId());
        assertEquals(board1.getTitle(), board2.getTitle());
        assertEquals(board1.getContent(), board2.getContent());
    }
}
