package com.samin.dosan.domain.setting.place_code.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.place_code.PlaceCode;
import com.samin.dosan.domain.setting.place_code.PlaceCodeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaceCodeRepositoryQueryDsl {
    Page<PlaceCode> findAll(SearchParam searchParam, PlaceCodeType placeCodeType, Pageable pageable);

    List<PlaceCode> findByLocation(PlaceCodeType placeCodeType, PlaceCode placeCode);
}
