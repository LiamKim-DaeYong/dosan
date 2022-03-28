package com.samin.dosan.domain.homepage.board;

import com.samin.dosan.domain.homepage.type.BoardType;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.board.repository.HomepageBoardRepository;
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
public class HomepageBoardService {

    private final HomepageBoardRepository homepageBoardRepository;

    public Page<HomepageBoard> findAll(SearchParam searchParam, Pageable pageable, BoardType boardType) {
        return homepageBoardRepository.findAll(searchParam, pageable, boardType);
    }

    public HomepageBoard findById(Long id) {
        return homepageBoardRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(HomepageBoard saveData) {
        return homepageBoardRepository.save(saveData).getId();
    }

    @Transactional
    public Long update(Long id, HomepageBoard updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
