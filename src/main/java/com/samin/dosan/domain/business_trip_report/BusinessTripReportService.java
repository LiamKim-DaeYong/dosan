package com.samin.dosan.domain.business_trip_report;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.business_trip_report.repository.BusinessTripRepository;
import com.samin.dosan.web.dto.business_trip_report.BusinessTripReportSave;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessTripReportService {

    private final BusinessTripRepository businessTripRepository;

    public Page<BusinessTripReport> findAll(SearchParam searchParam, Pageable pageable, String username) {
        return businessTripRepository.findAll(searchParam, pageable, username);
    }

    public BusinessTripReport findById(Long id) {
        return businessTripRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(BusinessTripReport businessTripReport) {
        return businessTripRepository.save(businessTripReport).getId();
    }

    @Transactional
    public Long update(Long id, BusinessTripReportSave businessTripReportSave) {
        findById(id).update(businessTripReportSave);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }

    @Transactional
    public void draft(Long id, ApprovalType approvalType) {
        findById(id).draft(approvalType);
    }
}
