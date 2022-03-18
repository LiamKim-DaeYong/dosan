package com.samin.dosan.domain.setting.former_code;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.former_code.repository.FormerRepository;
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
public class FormerService {

    private final FormerRepository formerRepository;

    public Page<FormerCode> findAll(SearchParam searchParam, Pageable pageable) {
        return formerRepository.findAll(searchParam, pageable);
    }

    public List<FormerCode> findAllList() {
        return formerRepository.findAllList();
    }

    public FormerCode findById(Long id) {
        return formerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public boolean valid(FormerCode formerCode) {
        return formerRepository.findByFormerName(formerCode).size() > 0;
    }

    @Transactional
    public Long save(FormerCode formerCode) {
        return formerRepository.save(formerCode).getId();
    }

    @Transactional
    public Long update(Long id, FormerCode updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
