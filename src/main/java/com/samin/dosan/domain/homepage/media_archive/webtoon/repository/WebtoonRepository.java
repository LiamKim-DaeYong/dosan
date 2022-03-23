package com.samin.dosan.domain.homepage.media_archive.webtoon.repository;

import com.samin.dosan.domain.homepage.media_archive.webtoon.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebtoonRepository extends JpaRepository<Webtoon, Long>, WebtoonRepositoryQueryDsl {
}
