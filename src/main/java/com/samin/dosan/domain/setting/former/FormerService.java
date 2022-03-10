package com.samin.dosan.domain.setting.former;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.former.repository.FormerRepository;
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

    public Page<Former> findAll(SearchParam searchParam, Pageable pageable) {
        return formerRepository.findAll(searchParam, pageable);
    }

    public List<Former> findAllList() {
        return formerRepository.findAllList();
    }

    public Former findById(Long id) {
        return formerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public boolean valid(Former former) {
        return formerRepository.findByFormerName(former).size() > 0;
    }

    @Transactional
    public Long save(Former former) {
        return formerRepository.save(former).getId();
    }

    @Transactional
    public Long update(Long id, Former updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
