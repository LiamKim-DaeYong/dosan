package com.samin.dosan.domain.homepage.mainImage.repository;

import com.samin.dosan.domain.homepage.mainImage.MainImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainImageRepository extends JpaRepository<MainImage, Long>, MainImageRepositoryQueryDsl {
}
