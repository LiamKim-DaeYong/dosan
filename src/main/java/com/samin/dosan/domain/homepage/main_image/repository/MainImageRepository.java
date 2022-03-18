package com.samin.dosan.domain.homepage.main_image.repository;

import com.samin.dosan.domain.homepage.main_image.MainImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainImageRepository extends JpaRepository<MainImage, Long>, MainImageRepositoryQueryDsl {
}
