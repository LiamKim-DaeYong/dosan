package com.samin.dosan.domain.clients.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.clients.Clients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientsRepositoryQueryDsl {

    Page<Clients> findAll(SearchParam searchParam, Pageable pageable);
}
