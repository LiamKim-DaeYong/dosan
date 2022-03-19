package com.samin.dosan.domain.setting.former_job_code;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.former_job_code.repository.FormerJobCodeRepository;
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
public class FormerJobCodeService {

    private final FormerJobCodeRepository formerJobCodeRepository;

    public Page<FormerJobCode> findAll(SearchParam searchParam, Pageable pageable) {
        return formerJobCodeRepository.findAll(searchParam, pageable);
    }

    public List<FormerJobCode> findAllList() {
        return formerJobCodeRepository.findAllList();
    }

    public FormerJobCode findById(Long id) {
        return formerJobCodeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public boolean valid(FormerJobCode formerCode) {
        return formerJobCodeRepository.findByFormerName(formerCode).size() > 0;
    }

    @Transactional
    public Long save(FormerJobCode formerCode) {
        return formerJobCodeRepository.save(formerCode).getId();
    }

    @Transactional
    public Long update(Long id, FormerJobCode updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
