package com.samin.dosan.domain.setting.place.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.place.Place;
import com.samin.dosan.domain.setting.place.PlaceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaceRepositoryQueryDsl {
    Page<Place> findAll(SearchParam searchParam, PlaceType placeType, Pageable pageable);

    List<Place> findByLocation(PlaceType placeType, Place place);
}
