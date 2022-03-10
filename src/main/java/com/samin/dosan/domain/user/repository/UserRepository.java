package com.samin.dosan.domain.user.repository;

import com.samin.dosan.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>, UserRepositoryQueryDSL {
}
