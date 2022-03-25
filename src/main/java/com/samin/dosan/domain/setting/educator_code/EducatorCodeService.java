package com.samin.dosan.domain.setting.educator_code;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.educator_code.repository.EducatorCodeRepository;
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
public class EducatorCodeService {

    private final EducatorCodeRepository educatorCodeRepository;

    public Page<EducatorCode> findAll(SearchParam searchParam, EducatorCodeType educatorCodeType, Pageable pageable) {
        return educatorCodeRepository.findAll(searchParam, educatorCodeType, pageable);
    }

    public List<EducatorCode> findAll(EducatorCodeType educatorCodeType) {
        return educatorCodeRepository.findAll(educatorCodeType);
    }

    public EducatorCode findById(Long id) {
        return educatorCodeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(EducatorCode educatorCode) {
        return educatorCodeRepository.save(educatorCode).getId();
    }

    @Transactional
    public Long update(Long id, EducatorCode updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
