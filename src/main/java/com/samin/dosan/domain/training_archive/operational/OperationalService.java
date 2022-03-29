package com.samin.dosan.domain.training_archive.operational;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training_archive.operational.repository.OperationalRepository;
import com.samin.dosan.web.dto.training_archive.OperationalSave;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OperationalService {

    private final OperationalRepository operationalRepository;

    public Page<Operational> findAll(SearchParam searchParam, Pageable pageable, String type) {
        return operationalRepository.findAll(searchParam, pageable, type);
    }

    public Operational findById(Long id) {
        return operationalRepository.findById(id)
                .orElseThrow(EntityExistsException::new);
    }

    @Transactional
    public Long save(Operational saveData) {
        return operationalRepository.save(saveData).getId();
    }
}
