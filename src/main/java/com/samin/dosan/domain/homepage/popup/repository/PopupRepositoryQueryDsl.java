package com.samin.dosan.domain.homepage.popup.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.popup.Popup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PopupRepositoryQueryDsl {
    Page<Popup> findAll(SearchParam searchParam, Pageable pageable);
}
