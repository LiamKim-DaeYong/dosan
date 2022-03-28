package com.samin.dosan.domain.training_archive.branch;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training_archive.branch.repository.BranchRepository;
import com.samin.dosan.web.dto.training_archive.BranchSave;
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
public class BranchService {

    private final BranchRepository branchRepository;

    public Page<Branch> findAll(SearchParam searchParam, Pageable pageable, String branchType) {
        return branchRepository.findAll(searchParam, pageable, branchType);
    }

    public Branch findById(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(Branch saveData) {
        return branchRepository.save(saveData).getId();
    }

    @Transactional
    public Long update(Long id, BranchSave updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
