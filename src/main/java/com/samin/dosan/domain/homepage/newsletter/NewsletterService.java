package com.samin.dosan.domain.homepage.newsletter;

import com.samin.dosan.core.homepage_core.BaseService;
import com.samin.dosan.domain.homepage.newsletter.repository.NewsletterRepository;
import org.springframework.stereotype.Service;

@Service
public class NewsletterService extends BaseService<Newsletter, Long> {

    private final NewsletterRepository newsletterRepository;

    public NewsletterService(NewsletterRepository newsletterRepository) {
        super(newsletterRepository);
        this.newsletterRepository = newsletterRepository;
    }
}
