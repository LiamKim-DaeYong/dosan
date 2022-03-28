package com.samin.dosan.domain.training_archive.branch.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training_archive.branch.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BranchRepositoryQueryDsl {

    Page<Branch> findAll(SearchParam searchParam, Pageable pageable, String branchType);
}
