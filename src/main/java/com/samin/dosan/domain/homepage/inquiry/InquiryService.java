package com.samin.dosan.domain.homepage.inquiry;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.inquiry.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    public Page<Inquiry> findAll(SearchParam searchParam, Pageable pageable) {
        return inquiryRepository.findAll(searchParam, pageable);
    }
//
//    @Transactional
//    public boolean inquiryDelete_admin(List<Long> idList) {
//        boolean result = false;
//        if (isNotEmpty(idList)) {
//            for (Long id : idList) {
//                inquiryRepository.deleteById(id);
//            }
//
//            result = true;
//        }
//
//        return result;
//    }
//
//    @Transactional
//    public Long inquiryCommentSave_admin(Map<String, String> commentVo) {
//        String username = "도산서원 선비문화수련원";
//
//        Long inquiryId = Long.valueOf(commentVo.get("inquiryId"));
//        String comment = commentVo.get("comment");
//
//        Inquiry inquiry = inquiryRepository.findById(inquiryId).get();
//        InquiryCommentRequest inquiryComment = new InquiryCommentRequest();
//        inquiryComment.setAuthor(username);
//        inquiryComment.setComment(comment);
//
//        InquiryCommentInputRequest commentInput = new InquiryCommentInputRequest();
//        Inquiry willSaveInquiry = commentInput.toEntity(inquiry, inquiryComment.toEntity());
//
//        Long id = save(willSaveInquiry).getId();
//
//        return id;
//    }
//
//    @Transactional
//    public Long inquiryCommentDelete_admin(Long id) {
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(qInquiry.inquiryComment.id.eq(id));
//
//        Inquiry inquiry = select().from(qInquiry)
//                .where(builder)
//                .fetchOne();
//
//        inquiry.deleteInquiryComment();
//        save(inquiry);
//
//        return inquiry.getId();
//    }
}
