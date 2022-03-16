package com.samin.dosan.domain.homepage.inquiry.comment.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.inquiry.comment.InquiryComment;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryCommentRepository extends BaseJpaQueryDSLRepository<InquiryComment, Long> {
}
