package com.samin.dosan.domain.homepage.newsletter.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NewsletterRepositoryImpl implements NewsletterRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;
}
