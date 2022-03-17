package com.samin.dosan.domain.setting.place.repository;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.TestConfig;
import com.samin.dosan.domain.setting.place.Place;
import com.samin.dosan.domain.setting.place.PlaceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestConfig.class)
class PlaceRepositoryTest {

    @Autowired PlaceRepository placeRepository;

    List<Place> placeList;
    List<Place> explrList;

    PlaceType training = PlaceType.TRAINING;
    PlaceType explr = PlaceType.EXPLR;

    SearchParam searchParam;
    Pageable pageable;

    @BeforeEach
    void init() {
        searchParam = new SearchParam();
        pageable = PageRequest.of(0, 20);

        placeList = Arrays.asList(
            Place.builder().location("장소1").placeType(training).used(Used.Y).build(),
            Place.builder().location("장소2").placeType(training).used(Used.Y).build(),
            Place.builder().location("장소3").placeType(training).used(Used.Y).build(),
            Place.builder().location("장소4").placeType(training).used(Used.Y).build(),
            Place.builder().location("장소5").placeType(training).used(Used.Y).build(),
            Place.builder().location("장소6").placeType(training).used(Used.Y).build(),
            Place.builder().location("장소7").placeType(training).used(Used.Y).build(),
            Place.builder().location("장소8").placeType(training).used(Used.Y).build(),
            Place.builder().location("장소9").placeType(training).used(Used.Y).build(),
            Place.builder().location("장소10").placeType(training).used(Used.Y).build(),

            Place.builder().location("장소21").placeType(training).used(Used.N).build(),
            Place.builder().location("장소22").placeType(training).used(Used.N).build(),
            Place.builder().location("장소23").placeType(training).used(Used.N).build()
        );

        placeRepository.saveAll(placeList);

        explrList = Arrays.asList(
                Place.builder().location("탐방지1").placeType(explr).used(Used.Y).build(),
                Place.builder().location("탐방지2").placeType(explr).used(Used.Y).build(),
                Place.builder().location("탐방지3").placeType(explr).used(Used.Y).build(),
                Place.builder().location("탐방지4").placeType(explr).used(Used.Y).build(),
                Place.builder().location("탐방지5").placeType(explr).used(Used.Y).build(),
                Place.builder().location("탐방지6").placeType(explr).used(Used.Y).build(),
                Place.builder().location("탐방지7").placeType(explr).used(Used.Y).build(),
                Place.builder().location("탐방지8").placeType(explr).used(Used.Y).build(),
                Place.builder().location("탐방지9").placeType(explr).used(Used.Y).build(),
                Place.builder().location("탐방지10").placeType(explr).used(Used.Y).build(),

                Place.builder().location("탐방지24").placeType(explr).used(Used.N).build(),
                Place.builder().location("탐방지25").placeType(explr).used(Used.N).build(),
                Place.builder().location("탐방지26").placeType(explr).used(Used.N).build(),
                Place.builder().location("탐방지27").placeType(explr).used(Used.N).build()
        );

        placeRepository.saveAll(explrList);
    }

    @Test
    @DisplayName("수련장소 조회 테스트")
    void findAllPlace() {
        List<Place> findList = placeRepository.findAll(searchParam, training, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);

        isEqualToPlace(placeList.get(0), findList.get(9));
        isEqualToPlace(placeList.get(1), findList.get(8));
        isEqualToPlace(placeList.get(2), findList.get(7));
        isEqualToPlace(placeList.get(3), findList.get(6));
        isEqualToPlace(placeList.get(4), findList.get(5));
        isEqualToPlace(placeList.get(5), findList.get(4));
        isEqualToPlace(placeList.get(6), findList.get(3));
        isEqualToPlace(placeList.get(7), findList.get(2));
        isEqualToPlace(placeList.get(8), findList.get(1));
        isEqualToPlace(placeList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("수련장소 검색 테스트")
    void searchPlace() {
        searchParam.setSearchWorld("탐방지");
        List<Place> findList = placeRepository.findAll(searchParam, training, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        searchParam.setSearchWorld("장소1");
        List<Place> findList2 = placeRepository.findAll(searchParam, training, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(2);

        isEqualToPlace(placeList.get(0), findList2.get(1));
        isEqualToPlace(placeList.get(9), findList2.get(0));
    }

    @Test
    @DisplayName("수련장소 페이징 테스트")
    void pagingPlace() {
        pageable = PageRequest.of(1, 10);
        List<Place> findList = placeRepository.findAll(searchParam, training, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        pageable = PageRequest.of(1, 5);
        List<Place> findList2 = placeRepository.findAll(searchParam, training, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(5);

        isEqualToPlace(placeList.get(0), findList2.get(4));
        isEqualToPlace(placeList.get(1), findList2.get(3));
        isEqualToPlace(placeList.get(2), findList2.get(2));
        isEqualToPlace(placeList.get(3), findList2.get(1));
        isEqualToPlace(placeList.get(4), findList2.get(0));
    }

    @Test
    @DisplayName("탐방지 조회 테스트")
    void findAllExplr() {
        List<Place> findList = placeRepository.findAll(searchParam, explr, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);
        isEqualToPlace(explrList.get(0), findList.get(9));
        isEqualToPlace(explrList.get(1), findList.get(8));
        isEqualToPlace(explrList.get(2), findList.get(7));
        isEqualToPlace(explrList.get(3), findList.get(6));
        isEqualToPlace(explrList.get(4), findList.get(5));
        isEqualToPlace(explrList.get(5), findList.get(4));
        isEqualToPlace(explrList.get(6), findList.get(3));
        isEqualToPlace(explrList.get(7), findList.get(2));
        isEqualToPlace(explrList.get(8), findList.get(1));
        isEqualToPlace(explrList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("탐방지 검색 테스트")
    void searchExplr() {
        searchParam.setSearchWorld("장소");
        List<Place> findList = placeRepository.findAll(searchParam, explr, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        searchParam.setSearchWorld("탐방지1");
        List<Place> findList2 = placeRepository.findAll(searchParam, explr, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(2);
        isEqualToPlace(explrList.get(0), findList2.get(1));
        isEqualToPlace(explrList.get(9), findList2.get(0));
    }

    @Test
    @DisplayName("PlaceRepository 탐방지 페이징 테스트")
    void pagingExplr() {
        pageable = PageRequest.of(1, 10);
        List<Place> findList = placeRepository.findAll(searchParam, explr, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        pageable = PageRequest.of(1, 5);
        List<Place> findList2 = placeRepository.findAll(searchParam, explr, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(5);

        isEqualToPlace(explrList.get(0), findList2.get(4));
        isEqualToPlace(explrList.get(1), findList2.get(3));
        isEqualToPlace(explrList.get(2), findList2.get(2));
        isEqualToPlace(explrList.get(3), findList2.get(1));
        isEqualToPlace(explrList.get(4), findList2.get(0));
    }

    private void isEqualToPlace(Place place, Place findPlace) {
        assertThat(findPlace.getLocation()).isEqualTo(place.getLocation());
        assertThat(findPlace.getPlaceType()).isEqualTo(place.getPlaceType());
        assertThat(findPlace.getUsed()).isEqualTo(place.getUsed());
    }
}
