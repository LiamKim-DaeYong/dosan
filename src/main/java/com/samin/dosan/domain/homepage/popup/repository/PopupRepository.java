package com.samin.dosan.domain.homepage.popup.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.popup.Popup;
import org.springframework.stereotype.Repository;

@Repository
public interface PopupRepository extends BaseJpaQueryDSLRepository<Popup, Long> {
}
