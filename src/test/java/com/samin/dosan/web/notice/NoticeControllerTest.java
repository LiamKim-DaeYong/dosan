package com.samin.dosan.web.notice;

import com.samin.dosan.config.security.SecurityConfig;
import com.samin.dosan.domain.notice.Notice;
import com.samin.dosan.domain.notice.NoticeService;
import com.samin.dosan.web.param.SearchParam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
@ContextConfiguration(classes = NoticeController.class)
@Import(SecurityConfig.class)
@WithMockUser(value = "admin", roles = {"ADMIN"})
public class NoticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoticeService noticeService;

    @MockBean
    private ModelMapper modelMapper;

    private SearchParam searchParam;
    private List<Notice> noticeList;
    private Notice notice1;
    private Notice notice2;
    private Notice notice3;

    @Test
    @DisplayName("공지사항 조회 페이지")
    void list() throws Exception {
        searchParam = new SearchParam();
        noticeList = Arrays.asList(
                Notice.builder().id(1L).title("제목1").content("내용1").commentsList(new ArrayList<>()).build(),
                Notice.builder().id(2L).title("제목2").content("내용2").commentsList(new ArrayList<>()).build(),
                Notice.builder().id(3L).title("제목3").content("내용3").commentsList(new ArrayList<>()).build(),
                Notice.builder().id(4L).title("제목4").content("내용4").commentsList(new ArrayList<>()).build(),
                Notice.builder().id(5L).title("제목5").content("내용5").commentsList(new ArrayList<>()).build(),
                Notice.builder().id(6L).title("제목6").content("내용6").commentsList(new ArrayList<>()).build()
        );

        given(noticeService.findAll(searchParam)).willReturn(noticeList);

        mockMvc.perform(
                    get("/notice")
                    .param("searchWorld", searchParam.getSearchWorld())
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("notice/noticeList"));
    }
}
