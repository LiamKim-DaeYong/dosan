package com.samin.dosan.domain.setting.place_code;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.place_code.repository.PlaceCodeRepository;
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
public class PlaceCodeService {

    private final PlaceCodeRepository placeCodeRepository;

    public Page<PlaceCode> findAll(SearchParam searchParam, PlaceCodeType placeCodeType, Pageable pageable) {
        return placeCodeRepository.findAll(searchParam, placeCodeType, pageable);
    }

    public PlaceCode findById(Long id) {
        return placeCodeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(PlaceCode placeCode) {
        return placeCodeRepository.save(placeCode).getId();
    }

    @Transactional
    public Long update(Long id, PlaceCode placeCodeData) {
        findById(id).update(placeCodeData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
