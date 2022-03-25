package com.samin.dosan.domain.clients.repository;

import com.samin.dosan.domain.clients.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long>, ClientsRepositoryQueryDsl {
}
