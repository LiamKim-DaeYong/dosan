package com.samin.dosan.domain.setting.official_seal.repository;

import com.samin.dosan.domain.setting.official_seal.OfficialSeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignatureRepository extends JpaRepository<OfficialSeal, Long>, SignatureRepositoryQueryDsl {
}
