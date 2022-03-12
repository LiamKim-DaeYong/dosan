package com.samin.dosan.domain.signature.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.domain.signature.Signature;
import com.samin.dosan.domain.signature.SignatureType;
import lombok.RequiredArgsConstructor;

import static com.samin.dosan.domain.signature.QSignature.signature;

@RequiredArgsConstructor
public class SignatureRepositoryImpl implements SignatureRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Signature findTypeOfSystem() {
        return queryFactory
                .selectFrom(signature)
                .where(signature.signatureType.eq(SignatureType.SYSTEM))
                .fetchOne();
    }
}
