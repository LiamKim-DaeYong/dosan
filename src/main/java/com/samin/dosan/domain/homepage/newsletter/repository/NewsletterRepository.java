package com.samin.dosan.domain.homepage.newsletter.repository;

import com.samin.dosan.domain.homepage.newsletter.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {
}
