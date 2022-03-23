package com.samin.dosan.domain.homepage.newsletter;

import com.samin.dosan.domain.homepage.newsletter.repository.NewsletterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;
}
