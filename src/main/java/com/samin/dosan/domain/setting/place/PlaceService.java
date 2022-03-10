package com.samin.dosan.domain.setting.place;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.place.repository.PlaceRepository;
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

    public Page<Place> findAll(SearchParam searchParam, PlaceType placeType, Pageable pageable) {
        return placeRepository.findAll(searchParam, placeType, pageable);
    }

    public Place findById(Long id) {
        return placeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public boolean valid(PlaceType placeType, Place place) {
        return placeRepository.findByLocation(placeType, place).size() > 0;
    }

    @Transactional
    public Long save(Place place) {
        return placeRepository.save(place).getId();
    }

    @Transactional
    public Long update(Long id, Place placeData) {
        findById(id).update(placeData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
