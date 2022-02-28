package com.samin.dosan.web.board;

import com.samin.dosan.config.security.SecurityConfig;
import com.samin.dosan.domain.board.Board;
import com.samin.dosan.domain.board.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ContextConfiguration(classes = BoardController.class)
@WithMockUser(value = "admin", roles = {"ADMIN"})
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @Test
    @DisplayName("게시판 조회 페이지")
    void list() throws Exception {
        mockMvc.perform(get("/board"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/boardList"));
    }

    @Test
    @DisplayName("게시판 상세 페이지")
    void detail() throws Exception {
        Board board1 = Board.builder().id(1L).title("제목1").content("내용1").build();
        Board board2 = Board.builder().id(2L).title("제목2").content("내용2").build();

        given(boardService.findById(board1.getId())).willReturn(board1);
        mockMvc.perform(get("/board/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/boardDetail"))
                .andExpect(model().attribute("board", board1));

        given(boardService.findById(board2.getId())).willReturn(board2);
        mockMvc.perform(get("/board/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/boardDetail"))
                .andExpect(model().attribute("board", board1));
    }

    @Test
    @DisplayName("게시판 등록 페이지")
    void addForm() throws Exception {
        mockMvc.perform(get("/board/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/boardAddForm"));
    }

    @Test
    @DisplayName("게시판 등록")
    void save() throws Exception {
        MultiValueMap<String, String> boardSaveForm = new LinkedMultiValueMap<>();
        boardSaveForm.add("title", "제목");
        boardSaveForm.add("content", "내용");

        given(boardService.save(any(Board.class))).willReturn(1L);

        mockMvc.perform(post("/board/add")
                .params(boardSaveForm).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/1"));
    }

    @Test
    @WithMockUser
    @DisplayName("게시판 등록 페이지 Forbidden")
    void addFormForbidden() throws Exception {
        mockMvc.perform(get("/board/add"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    @DisplayName("게시판 등록 Forbidden")
    void saveForbidden() throws Exception {
        MultiValueMap<String, String> boardSaveForm = new LinkedMultiValueMap<>();
        boardSaveForm.add("title", "제목");
        boardSaveForm.add("content", "내용");

        mockMvc.perform(post("/board/add")
                .params(boardSaveForm).with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("게시판 수정 페이지")
    void editForm() throws Exception {
        Board board = Board.builder()
                .id(1L)
                .title("제목1")
                .content("내용1")
                .build();

        when(boardService.findById(anyLong())).thenReturn(board);

        mockMvc.perform(get("/board/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/boardEditForm"))
                .andExpect(model().attribute("board", board));
    }

    @Test
    @WithMockUser
    @DisplayName("게시판 수정 페이지 Forbidden")
    void editFormForbidden() throws Exception {
        Board board = Board.builder()
                .id(1L)
                .title("제목1")
                .content("내용1")
                .build();

        when(boardService.findById(anyLong())).thenReturn(board);

        mockMvc.perform(get("/board/1/edit"))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/board/2/edit"))
                .andExpect(status().isForbidden());
    }
}
