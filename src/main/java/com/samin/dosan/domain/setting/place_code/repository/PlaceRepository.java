package com.samin.dosan.domain.setting.place_code.repository;

import com.samin.dosan.domain.setting.place_code.PlaceCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceCode, Long>, PlaceRepositoryQueryDsl {
}
