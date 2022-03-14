package com.samin.dosan.domain.setting.employees;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.employees.repository.EmployeesCodeRepository;
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
public class EmployeesCodeService {

    private final EmployeesCodeRepository employeesCodeRepository;

    public Page<EmployeesCode> findAll(SearchParam searchParam, EmployeesCodeType employeesCodeType, Pageable pageable) {
        return employeesCodeRepository.findAll(searchParam, employeesCodeType, pageable);
    }

    public List<EmployeesCode> findAllTypes() {
        return employeesCodeRepository.findAllTypes();
    }

    public List<EmployeesCode> findAllPosition() {
        return employeesCodeRepository.findAllPosition();
    }

    public List<EmployeesCode> findAllRank() {
        return employeesCodeRepository.findAllRank();
    }

    public List<EmployeesCode> findAllStep() {
        return employeesCodeRepository.findAllStep();
    }

    public List<EmployeesCode> findAllDepartment() {
        return employeesCodeRepository.findAllDepartment();
    }

    public EmployeesCode findById(Long id) {
        return employeesCodeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(EmployeesCode employeesCode) {
        return employeesCodeRepository.save(employeesCode).getId();
    }

    @Transactional
    public Long update(Long id, EmployeesCode updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
