package com.samin.dosan.domain.setting.place_code.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.place_code.PlaceCode;
import com.samin.dosan.domain.setting.place_code.PlaceCodeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceCodeRepositoryQueryDsl {
    Page<PlaceCode> findAll(SearchParam searchParam, PlaceCodeType placeCodeType, Pageable pageable);
}
