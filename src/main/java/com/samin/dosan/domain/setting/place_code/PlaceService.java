package com.samin.dosan.domain.setting.place_code;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.place_code.repository.PlaceRepository;
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
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Page<PlaceCode> findAll(SearchParam searchParam, PlaceCodeType placeCodeType, Pageable pageable) {
        return placeRepository.findAll(searchParam, placeCodeType, pageable);
    }

    public PlaceCode findById(Long id) {
        return placeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public boolean valid(PlaceCodeType placeCodeType, PlaceCode placeCode) {
        return placeRepository.findByLocation(placeCodeType, placeCode).size() > 0;
    }

    @Transactional
    public Long save(PlaceCode placeCode) {
        return placeRepository.save(placeCode).getId();
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
