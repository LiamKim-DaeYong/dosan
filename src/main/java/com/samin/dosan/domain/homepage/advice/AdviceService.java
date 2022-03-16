package com.samin.dosan.domain.homepage.advice;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.samin.dosan.core.homepage_core.BaseService;
import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.advice.dto.AdviceChangeCheckRequest;
import com.samin.dosan.domain.homepage.advice.dto.AdviceResponse;
import com.samin.dosan.domain.homepage.advice.repository.AdviceRepository;
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
public class AdviceService extends BaseService<Advice, Long> {

    private AdviceRepository adviceRepository;


    public AdviceService(AdviceRepository adviceRepository) {
        super(adviceRepository);
        this.adviceRepository = adviceRepository;
    }

    public Page<AdviceResponse> gets(int page, FilterDto filterDto) {
        Pageable pageable = PageRequest.of(page-1, 50);
        BooleanBuilder builder = new BooleanBuilder();
        LocalDate now = LocalDate.now();

        if (filterDto.getFilter() == null && filterDto.getStart() == null && filterDto.getEnd() == null) {
            builder.and(qAdvice.regDt.eq(now));
        } else {
            if (filterDto.getSelect() != null && !filterDto.getSelect().equals("")) {
                switch (filterDto.getSelect()) {
                    case "all":
                        break;
                    case "sunbi":
                        builder.and(qAdvice.adviceType.eq("찾아가는 선비체험"));
                        break;
                    case "admission":
                        builder.and(qAdvice.adviceType.eq("입교과정"));
                        break;
                    case "family":
                        builder.and(qAdvice.adviceType.eq("가족"));
                        break;
                    case "ordinary":
                        builder.and(qAdvice.adviceType.eq("일반인"));
                        break;
                    case "uncheck":
                        builder.and(qAdvice.status.eq("N"));
                        break;
                    case "check":
                        builder.and(qAdvice.status.eq("Y"));
                        break;
                }

                if (filterDto.getFilter() != null && !filterDto.getFilter().equals("")) {
                    builder.and(qAdvice.applicant.contains(filterDto.getFilter()));
                    builder.or(qAdvice.insttNm.contains(filterDto.getFilter()));
                    builder.or(qAdvice.depart.contains(filterDto.getFilter()));
                }
            }

            if (filterDto.getStart() != null && !filterDto.getStart().equals("")) {
                builder.and(qAdvice.regDt.goe(LocalDate.parse(filterDto.getStart())));
            }

            if (filterDto.getEnd() != null && !filterDto.getEnd().equals("")) {
                builder.and(qAdvice.regDt.loe(LocalDate.parse(filterDto.getEnd())));
            }
        }

        QueryResults<Advice> results = select().from(qAdvice)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qAdvice.status.desc(), qAdvice.regDt.desc(), qAdvice.id.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults().stream().map(advice -> new AdviceResponse(advice))
                .collect(Collectors.toList()), pageable, results.getTotal());
    }

    public boolean adviceCheck(Long id) {
        boolean result = false;
        Advice advice = repository.findById(id).get();

        Advice savedAdvice = save(new AdviceChangeCheckRequest(advice, "Y").toEntity());
        if (savedAdvice != null) {
            result = true;
        }

        return result;
    }

    @Transactional
    public boolean adviceDelete(List<Long> idList) {
        boolean result = false;
        if (isNotEmpty(idList)) {
            for (Long id : idList) {
                adviceRepository.deleteById(id);
            }

            result = true;
        }

        return result;
    }
}
