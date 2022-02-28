package com.samin.dosan.domain.board;

import com.samin.dosan.domain.board.repository.BoardRepository;
import com.samin.dosan.web.param.SearchParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import javax.transaction.NotSupportedException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

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
    @DisplayName("게시판 조회")
    void findAll() {
        //given
        when(boardRepository.findAll(searchParam)).thenReturn(Arrays.asList(board1, board2, board3));

        //when
        List<Board> findBoardList = boardService.findAll(searchParam);

        //then
        assertEquals(3, findBoardList.size());
        isEquals(board1, findBoardList.get(0));
        isEquals(board2, findBoardList.get(1));
        isEquals(board3, findBoardList.get(2));
    }

    @Test
    @DisplayName("게시판 검색")
    void search() {
        //given
        searchParam.setSearchWorld("1");
        when(boardRepository.findAll(searchParam)).thenReturn(Arrays.asList(board1, board2, board3)
            .stream().filter(board -> board.getTitle().contains(searchParam.getSearchWorld()))
                .collect(Collectors.toList()));

        //when
        List<Board> findBoardList = boardService.findAll(searchParam);

        //then
        assertEquals(1, findBoardList.size());
        isEquals(board1, findBoardList.get(0));

    }

    @Test
    @DisplayName("게시판 저장")
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
