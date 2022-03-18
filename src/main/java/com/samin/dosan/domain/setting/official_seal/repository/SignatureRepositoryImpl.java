package com.samin.dosan.domain.setting.official_seal.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.domain.setting.official_seal.OfficialSeal;
import com.samin.dosan.domain.setting.official_seal.OfficialSealType;
import lombok.RequiredArgsConstructor;

import static com.samin.dosan.domain.setting.official_seal.QOfficialSeal.officialSeal;

@RequiredArgsConstructor
public class SignatureRepositoryImpl implements SignatureRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public OfficialSeal findTypeOfSystem() {
        return queryFactory
                .selectFrom(officialSeal)
                .where(officialSeal.officialSealType.eq(OfficialSealType.SYSTEM))
                .fetchOne();
    }
}
