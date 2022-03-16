package com.samin.dosan.domain.homepage.newsletter.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.newsletter.Newsletter;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsletterRepository extends BaseJpaQueryDSLRepository<Newsletter, Long> {
}
