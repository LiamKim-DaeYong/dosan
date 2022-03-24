package com.samin.dosan.domain.business_trip_report.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.business_trip_report.BusinessTripReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BusinessTripRepositoryQueryDsl {
    Page<BusinessTripReport> findAll(SearchParam searchParam, Pageable pageable, String username);
}
