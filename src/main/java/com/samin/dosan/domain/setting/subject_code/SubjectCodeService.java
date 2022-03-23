package com.samin.dosan.domain.setting.subject_code;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.subject_code.repository.SubjectCodeRepository;
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
public class SubjectCodeService {

    private final SubjectCodeRepository subjectCodeRepository;

    public Page<SubjectCode> findAll(SearchParam searchParam, SubjectCodeType subjectCodeType, Pageable pageable) {
        return subjectCodeRepository.findAll(searchParam, subjectCodeType, pageable);
    }

    public SubjectCode findById(Long id) {
        return subjectCodeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(SubjectCode saveData) {
        subjectCodeRepository.save(saveData);
        return saveData.getId();
    }

    @Transactional
    public Long update(Long id, SubjectCode updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
