package com.samin.dosan.domain.setting.employee_code;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.employee_code.repository.EmployeeCodeRepository;
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
public class EmployeeCodeService {

    private final EmployeeCodeRepository employeeCodeRepository;

    public Page<EmployeeCode> findAll(SearchParam searchParam, EmployeeCodeType employeeCodeType, Pageable pageable) {
        return employeeCodeRepository.findAll(searchParam, employeeCodeType, pageable);
    }

    public List<EmployeeCode> findAll(EmployeeCodeType employeeCodeType) {
        return employeeCodeRepository.findAll(employeeCodeType);
    }

    public EmployeeCode findById(Long id) {
        return employeeCodeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public EmployeeCode findByCode(String code) {
        return employeeCodeRepository.findByCode(code);
    }

    @Transactional
    public Long save(EmployeeCode employeeCode) {
        return employeeCodeRepository.save(employeeCode).getId();
    }

    @Transactional
    public Long update(Long id, EmployeeCode updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
