package com.samin.dosan.domain.setting.place.repository;

import com.samin.dosan.domain.setting.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryQueryDsl {
}
