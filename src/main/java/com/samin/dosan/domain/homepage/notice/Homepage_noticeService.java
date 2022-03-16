package com.samin.dosan.domain.homepage.notice;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.samin.dosan.core.homepage_core.BaseService;
import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.notice.dto.NoticeRequest;
import com.samin.dosan.domain.homepage.notice.dto.NoticeResponse;
import com.samin.dosan.domain.homepage.notice.repository.Homepage_NoticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Homepage_noticeService extends BaseService<Homepage_Notice, Long> {

    private Homepage_NoticeRepository noticeRepository;

    public Homepage_noticeService(Homepage_NoticeRepository noticeRepository) {
        super(noticeRepository);
        this.noticeRepository = noticeRepository;
    }

    public Page<NoticeResponse> getNoticeList_admin(int page, FilterDto filterDto) {
        Pageable pageable = PageRequest.of(page-1, 15);
        BooleanBuilder builder = new BooleanBuilder();

        if (filterDto.getFilter() == null && filterDto.getStart() == null && filterDto.getEnd() == null) {
            builder = new BooleanBuilder();
        } else {
            if (filterDto.getFilter() != null && !filterDto.getFilter().equals("")) {
                builder.and(qNotice.title.contains(filterDto.getFilter()));
            }

            if (filterDto.getStart() != null && !filterDto.getStart().equals("")) {
                builder.and(qNotice.regDt.goe(LocalDate.parse(filterDto.getStart())));
            }

            if (filterDto.getEnd() != null && !filterDto.getEnd().equals("")) {
                builder.and(qNotice.regDt.loe(LocalDate.parse(filterDto.getEnd())));
            }
        }

        QueryResults<Homepage_Notice> results = select().from(qNotice)
                .where(builder)
                .leftJoin(qNotice.childList, qNoticeFile)
                .fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qNotice.regDt.desc(), qNotice.id.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults().stream().map(notice -> new NoticeResponse(notice))
                .collect(Collectors.toList()), pageable, results.getTotal());
    }

    public NoticeResponse getNotice_admin(Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qNotice.id.eq(id));

        Homepage_Notice notice = select().from(qNotice)
                .where(builder)
                .leftJoin(qNotice.childList, qNoticeFile)
                .fetchJoin()
                .fetchOne();

         return new NoticeResponse(notice);
    }

    @Transactional
    public Long noticeSave_admin(NoticeRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "도산서원 선비문화수련원";

        Long id = null;
        if (request != null) {
            request.setAuthor(username);
            id = save(request.toEntity()).getId();
        }

        return id;
    }

    @Transactional
    public boolean noticeDelete_admin(List<Long> idList) {
        boolean result = false;

        if (isNotEmpty(idList)) {
            for (Long id : idList) {
                noticeRepository.deleteById(id);
            }
            result = true;
        }

        return result;
    }
}
