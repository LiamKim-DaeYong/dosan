package com.samin.dosan.domain.business_trip_report.repository;

import com.samin.dosan.domain.business_trip_report.BusinessTripReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessTripRepository extends JpaRepository<BusinessTripReport, Long>, BusinessTripRepositoryQueryDsl {
}
