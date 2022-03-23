package com.samin.dosan.domain.homepage.media_archive.promotional_video;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.media_archive.promotional_video.repository.PromotionalVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionalVideoService {

    private final PromotionalVideoRepository promotionalVideoRepository;

    public Page<PromotionalVideo> findAll(SearchParam searchParam, Pageable pageable) {
        return promotionalVideoRepository.findAll(searchParam, pageable);
    }

    public PromotionalVideo findById(Long id) {
        return promotionalVideoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(PromotionalVideo saveData) {
        return promotionalVideoRepository.save(saveData).getId();
    }

    @Transactional
    public Long update(Long id, PromotionalVideo updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
