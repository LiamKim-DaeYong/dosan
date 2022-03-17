package com.samin.dosan.domain.setting.place;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.setting.place.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PlaceServiceTest {

    PlaceRepository placeRepository;
    PlaceService placeService;

    Place training;
    Place explr;

    @BeforeEach
    void init() {
        placeRepository = mock(PlaceRepository.class);
        placeService = new PlaceService(placeRepository);

        training = Place.builder()
                .id(1L)
                .location("수련 장소1")
                .placeType(PlaceType.TRAINING)
                .used(Used.Y)
                .build();

        explr = Place.builder()
                .id(2L)
                .location("탐방지1")
                .placeType(PlaceType.EXPLR)
                .used(Used.Y)
                .build();

        when(placeRepository.findById(1L)).thenReturn(Optional.of(training));
        when(placeRepository.findById(2L)).thenReturn(Optional.of(explr));
        when(placeRepository.findById(3L)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("단일 조회 테스트")
    void findById() {
        Place findTraining = placeService.findById(1L);
        isEqualToPlace(training, findTraining);

        Place findExplr = placeService.findById(2L);
        isEqualToPlace(explr, findExplr);

        assertThrows(EntityNotFoundException.class, () -> placeService.findById(3L));
        verify(placeRepository, times(3)).findById(anyLong());
    }

    @Test
    @DisplayName("수정 테스트")
    void update() {
        Place updateData = Place.builder()
                .location("장소 수정")
                .placeType(PlaceType.TRAINING)
                .used(Used.Y)
                .build();

        Long updateId = placeService.update(1L, updateData);
        verify(placeRepository, times(1)).findById(anyLong());

        Place update = placeService.findById(updateId);
        isEqualToPlace(update, updateData);
    }

    @Test
    @DisplayName("삭제 테스트")
    void delete() {
        List<Long> ids = Arrays.asList(1L, 2L);
        placeService.delete(ids);
        verify(placeRepository, times(2)).findById(anyLong());

        Place delete1 = placeService.findById(1L);
        Place delete2 = placeService.findById(2L);

        assertThat(delete1.getUsed()).isEqualTo(Used.N);
        assertThat(delete2.getUsed()).isEqualTo(Used.N);
    }

    private void isEqualToPlace(Place place, Place findPlace) {
        assertThat(findPlace.getLocation()).isEqualTo(place.getLocation());
        assertThat(findPlace.getPlaceType()).isEqualTo(place.getPlaceType());
        assertThat(findPlace.getUsed()).isEqualTo(place.getUsed());
    }
}
