package com.samin.dosan.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<Files, Long> {
}
