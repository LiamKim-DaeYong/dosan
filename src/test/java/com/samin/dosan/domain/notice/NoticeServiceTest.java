package com.samin.dosan.domain.notice;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.domain.notice.repository.NoticeRepository;
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

import static com.samin.dosan.domain.notice.QNotice.notice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoticeServiceTest {

    @Mock
    private NoticeRepository noticeRepository;

    @Mock
    private JPAQueryFactory queryFactory;

    @Mock(answer = Answers.RETURNS_SELF)
    private JPAQuery query;

    @InjectMocks
    private NoticeService noticeService;

    private Notice notice1;
    private Notice notice2;
    private Notice notice3;
    private SearchParam searchParam;

    @BeforeEach
    void init() {
        searchParam = new SearchParam();
        notice1 = Notice.builder().id(1L).title("제목1").content("내용1").build();
        notice2 = Notice.builder().id(2L).title("제목2").content("내용2").build();
        notice3 = Notice.builder().id(3L).title("제목3").content("내용3").build();
    }

    @Test
    @DisplayName("공지사항 조회 테스트")
    void findAll() {
        when(queryFactory.selectFrom(notice)).thenReturn(query);
        when(query.fetch()).thenReturn(Arrays.asList(notice1, notice2, notice3));

        List<Notice> findNoticeList = noticeService.findAll(searchParam);

        assertEquals(3, findNoticeList.size());
        isEquals(notice1, findNoticeList.get(0));
        isEquals(notice2, findNoticeList.get(1));
        isEquals(notice3, findNoticeList.get(2));
    }

    @Test
    @DisplayName("공지사항 단일정보 조회 테스트")
    void findById() {
        when(queryFactory.selectFrom(notice)).thenReturn(query);
        when(query.fetch()).thenReturn(Arrays.asList(notice1, notice2, notice3));


    }

    private void isEquals(Notice notice1, Notice notice2) {
        assertEquals(notice1.getId(), notice2.getId());
        assertEquals(notice1.getTitle(), notice2.getTitle());
        assertEquals(notice1.getContent(), notice2.getContent());
    }
}
