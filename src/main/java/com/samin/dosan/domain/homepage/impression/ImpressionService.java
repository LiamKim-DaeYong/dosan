package com.samin.dosan.domain.homepage.impression;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.samin.dosan.core.homepage_core.BaseService;
import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.impression.dto.ImpressionResponse;
import com.samin.dosan.domain.homepage.impression.repository.ImpressionRepository;
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
public class ImpressionService extends BaseService<Impression, Long> {

    private ImpressionRepository impressionRepository;

    public ImpressionService(ImpressionRepository impressionRepository) {
        super(impressionRepository);
        this.impressionRepository = impressionRepository;
    }

    public Page<ImpressionResponse> getImpressionList_admin(int page, FilterDto filterDto) {
        Pageable pageable = PageRequest.of(page-1, 15);
        BooleanBuilder builder = new BooleanBuilder();

        if (filterDto.getFilter() == null && filterDto.getStart() == null && filterDto.getEnd() == null) {
            builder = new BooleanBuilder();
        } else {
            if (filterDto.getFilter() != null && !filterDto.getFilter().equals("")) {
                builder.and(qImpression.title.contains(filterDto.getFilter()));
            }

            if (filterDto.getStart() != null && !filterDto.getStart().equals("")) {
                builder.and(qImpression.regDt.goe(LocalDate.parse(filterDto.getStart())));
            }

            if (filterDto.getEnd() != null && !filterDto.getEnd().equals("")) {
                builder.and(qImpression.regDt.loe(LocalDate.parse(filterDto.getEnd())));
            }
        }

        QueryResults<Impression> results = select().from(qImpression)
                .where(builder)
                .leftJoin(qImpression.impressionFileList, qImpressionFile)
                .fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qImpression.regDt.desc(), qImpression.id.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults().stream().map(imp -> new ImpressionResponse(imp))
                .collect(Collectors.toList()), pageable, results.getTotal());
    }

    public ImpressionResponse getImpression_admin(Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qImpression.id.eq(id));

        Impression impression = select().from(qImpression)
                .where(builder)
                .leftJoin(qImpression.impressionFileList, qImpressionFile)
                .fetchJoin()
                .fetchOne();

        return new ImpressionResponse(impression);
    }

    @Transactional
    public boolean impressionDelete_admin(List<Long> idList) {
        boolean result = false;

        if (isNotEmpty(idList)) {
            for (Long id : idList) {
                impressionRepository.deleteById(id);
            }

            result = true;
        }


        return result;
    }
}
