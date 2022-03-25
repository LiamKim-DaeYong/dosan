package com.samin.dosan.domain.user.educator;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.educator.entity.Educator;
import com.samin.dosan.domain.user.educator.repository.EducatorRepository;
import com.samin.dosan.web.dto.user.educator.EducatorUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EducatorService {

    private final EducatorRepository educatorRepository;

    public Page<Educator> findAll(SearchParam searchParam, Long educatorCodeId, Pageable pageable) {
        return educatorRepository.findAll(searchParam, educatorCodeId, pageable);
    }

    public Educator findByUserId(String userId) {
        return educatorRepository.findByUserId(userId);
    }

    @Transactional
    public void save(Educator educator) {
        educatorRepository.save(educator);
    }

    @Transactional
    public void update(String userId, EducatorUpdate updateData) {
        Educator educator = findByUserId(userId);
        educator.update(updateData);
    }
}
