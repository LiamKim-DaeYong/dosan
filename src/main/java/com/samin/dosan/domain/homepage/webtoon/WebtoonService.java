package com.samin.dosan.domain.homepage.webtoon;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.samin.dosan.core.homepage_core.BaseService;
import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.webtoon.dto.WebtoonRequest;
import com.samin.dosan.domain.homepage.webtoon.dto.WebtoonResponse;
import com.samin.dosan.domain.homepage.webtoon.repository.WebtoonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebtoonService extends BaseService<Webtoon, Long> {

    private WebtoonRepository webtoonRepository;

    public WebtoonService(WebtoonRepository webtoonRepository) {
        super(webtoonRepository);
        this.webtoonRepository = webtoonRepository;
    }

    public Page<WebtoonResponse> getWebtoonList_admin(int page, FilterDto filterDto) {
        Pageable pageable = PageRequest.of(page-1, 15);
        BooleanBuilder builder = new BooleanBuilder();

        if (filterDto.getFilter() == null && filterDto.getStart() == null && filterDto.getEnd() == null) {
            builder = new BooleanBuilder();
        } else {
            if (filterDto.getFilter() != null && !filterDto.getFilter().equals("")) {
                builder.and(qWebtoon.title.contains(filterDto.getFilter()));
            }

            if (filterDto.getStart() != null && !filterDto.getStart().equals("")) {
                builder.and(qWebtoon.regDt.goe(LocalDate.parse(filterDto.getStart())));
            }

            if (filterDto.getEnd() != null && !filterDto.getEnd().equals("")) {
                builder.and(qWebtoon.regDt.loe(LocalDate.parse(filterDto.getEnd())));
            }
        }

        QueryResults<Webtoon> results = select().from(qWebtoon)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qWebtoon.regDt.desc(), qWebtoon.id.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults().stream().map(web -> new WebtoonResponse(web))
                .collect(Collectors.toList()), pageable, results.getTotal());
    }

    public WebtoonResponse getWebtoon_admin(Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qWebtoon.id.eq(id));

        Webtoon webtoon = select().from(qWebtoon)
                .where(builder)
                .fetchOne();

        return new WebtoonResponse(webtoon);
    }

    @Transactional
    public Long webtoonSave_admin(WebtoonRequest request) {
        String username = "도산서원 선비문화수련원";

        Long id = null;
        if (request != null) {
            request.setAuthor(username);
            id = save(request.toEntity()).getId();
        }

        return id;
    }

    @Transactional
    public boolean webtoonDelete_admin(List<Long> idList) {
        boolean result = false;
        if (isNotEmpty(idList)) {
            for (Long id : idList) {
                webtoonRepository.deleteById(id);
            }

            result = true;
        }

        return result;
    }
}
