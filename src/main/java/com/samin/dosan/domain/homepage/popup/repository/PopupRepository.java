package com.samin.dosan.domain.homepage.popup.repository;

import com.samin.dosan.domain.homepage.popup.Popup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopupRepository extends JpaRepository<Popup, Long>, PopupRepositoryQueryDsl {
}
