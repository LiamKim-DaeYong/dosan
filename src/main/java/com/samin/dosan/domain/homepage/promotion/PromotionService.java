package com.samin.dosan.domain.homepage.promotion;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.samin.dosan.core.homepage_core.BaseService;
import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.promotion.dto.PromotionRequest;
import com.samin.dosan.domain.homepage.promotion.dto.PromotionResponse;
import com.samin.dosan.domain.homepage.promotion.repository.PromotionRepository;
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
public class PromotionService extends BaseService<Promotion, Long> {

    private PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        super(promotionRepository);
        this.promotionRepository = promotionRepository;
    }

    public Page<PromotionResponse> getPromotionList_admin(int page, FilterDto filterDto) {
        Pageable pageable = PageRequest.of(page-1, 15);
        BooleanBuilder builder = new BooleanBuilder();

        if (filterDto.getFilter() == null && filterDto.getStart() == null && filterDto.getEnd() == null) {
            builder = new BooleanBuilder();
        } else {
            if (filterDto.getFilter() != null && !filterDto.getFilter().equals("")) {
                builder.and(qPromotion.title.contains(filterDto.getFilter()));
            }

            if (filterDto.getStart() != null && !filterDto.getStart().equals("")) {
                builder.and(qPromotion.regDt.goe(LocalDate.parse(filterDto.getStart())));
            }

            if (filterDto.getEnd() != null && !filterDto.getEnd().equals("")) {
                builder.and(qPromotion.regDt.loe(LocalDate.parse(filterDto.getEnd())));
            }
        }

        QueryResults<Promotion> results = select().from(qPromotion)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qPromotion.regDt.desc(), qPromotion.id.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults().stream().map(pro -> new PromotionResponse(pro))
                .collect(Collectors.toList()), pageable, results.getTotal());
    }

    public PromotionResponse getPromotion_admin(Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qPromotion.id.eq(id));

        Promotion promotion = select().from(qPromotion)
                .where(builder)
                .fetchOne();

        return new PromotionResponse(promotion);
    }

    @Transactional
    public Long promotionSave_admin(PromotionRequest request) {
        String username = "도산서원 선비문화수련원";

        Long id = null;
        if (request != null) {
            request.setAuthor(username);
            id = save(request.toEntity()).getId();
        }

        return id;
    }

    @Transactional
    public boolean promotionDelete_admin(List<Long> idList) {
        boolean result = false;

        if (isNotEmpty(idList)) {
            for (Long id : idList) {
                promotionRepository.deleteById(id);
            }
            result = true;
        }

        return result;
    }
}
