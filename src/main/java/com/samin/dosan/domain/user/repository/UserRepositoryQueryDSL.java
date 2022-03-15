package com.samin.dosan.domain.user.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryQueryDSL {
    Page<User> findAllEmployees(SearchParam searchParam, Long employeesType, Pageable pageable);

    boolean existById(String userId);
}
