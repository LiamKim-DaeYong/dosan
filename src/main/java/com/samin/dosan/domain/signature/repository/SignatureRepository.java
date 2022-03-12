package com.samin.dosan.domain.signature.repository;

import com.samin.dosan.domain.signature.Signature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignatureRepository extends JpaRepository<Signature, Long>, SignatureRepositoryQueryDsl {
}
