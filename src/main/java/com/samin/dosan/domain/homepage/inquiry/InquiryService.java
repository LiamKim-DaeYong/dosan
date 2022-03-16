package com.samin.dosan.domain.homepage.inquiry;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.samin.dosan.core.homepage_core.BaseService;
import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.inquiry.dto.InquiryCommentInputRequest;
import com.samin.dosan.domain.homepage.inquiry.dto.InquiryCommentRequest;
import com.samin.dosan.domain.homepage.inquiry.dto.InquiryResponse;
import com.samin.dosan.domain.homepage.inquiry.repository.InquiryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InquiryService extends BaseService<Inquiry, Long> {

    private InquiryRepository inquiryRepository;

    public InquiryService(InquiryRepository inquiryRepository) {
        super(inquiryRepository);
        this.inquiryRepository = inquiryRepository;
    }

    public Page<InquiryResponse> getInquiryList_admin(int page, FilterDto filterDto) {
        Pageable pageable = PageRequest.of(page-1, 15);
        BooleanBuilder builder = new BooleanBuilder();

        if (filterDto.getFilter() == null && filterDto.getStart() == null && filterDto.getEnd() == null) {
            builder = new BooleanBuilder();
        } else {
            if (filterDto.getFilter() != null && !filterDto.getFilter().equals("")) {
                builder.and(qInquiry.title.contains(filterDto.getFilter()));
            }

            if (filterDto.getStart() != null && !filterDto.getStart().equals("")) {
                builder.and(qInquiry.regDt.goe(LocalDate.parse(filterDto.getStart())));
            }

            if (filterDto.getEnd() != null && !filterDto.getEnd().equals("")) {
                builder.and(qInquiry.regDt.loe(LocalDate.parse(filterDto.getEnd())));
            }
        }

        QueryResults<Inquiry> results = select().from(qInquiry)
                .where(builder)
                .leftJoin(qInquiry.inquiryFileList, qInquiryFile)
                .fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qInquiry.regDt.desc(), qInquiry.id.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults().stream().map(inq -> new InquiryResponse(inq))
                .collect(Collectors.toList()), pageable, results.getTotal());
    }

    public InquiryResponse getInquiry_admin(Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qInquiry.id.eq(id));

        Inquiry inquiry = select().from(qInquiry)
                .where(builder)
                .leftJoin(qInquiry.inquiryFileList, qInquiryFile)
                .fetchJoin()
                .fetchOne();

        return new InquiryResponse(inquiry);
    }

    @Transactional
    public boolean inquiryDelete_admin(List<Long> idList) {
        boolean result = false;
        if (isNotEmpty(idList)) {
            for (Long id : idList) {
                inquiryRepository.deleteById(id);
            }

            result = true;
        }

        return result;
    }

    @Transactional
    public Long inquiryCommentSave_admin(Map<String, String> commentVo) {
        String username = "도산서원 선비문화수련원";

        Long inquiryId = Long.valueOf(commentVo.get("inquiryId"));
        String comment = commentVo.get("comment");

        Inquiry inquiry = inquiryRepository.findById(inquiryId).get();
        InquiryCommentRequest inquiryComment = new InquiryCommentRequest();
        inquiryComment.setAuthor(username);
        inquiryComment.setComment(comment);

        InquiryCommentInputRequest commentInput = new InquiryCommentInputRequest();
        Inquiry willSaveInquiry = commentInput.toEntity(inquiry, inquiryComment.toEntity());

        Long id = save(willSaveInquiry).getId();

        return id;
    }

    @Transactional
    public Long inquiryCommentDelete_admin(Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qInquiry.inquiryComment.id.eq(id));

        Inquiry inquiry = select().from(qInquiry)
                .where(builder)
                .fetchOne();

        inquiry.deleteInquiryComment();
        save(inquiry);

        return inquiry.getId();
    }
}
