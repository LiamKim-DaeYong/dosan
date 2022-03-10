package com.samin.dosan.domain.setting.place;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "place_id")
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private PlaceType placeType;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public Place init(String type) {
        this.placeType = PlaceType.valueOf(type.toUpperCase());
        this.used = Used.Y;

        return this;
    }

    public void update(Place placeData) {
        this.location = placeData.getLocation();
    }

    public void delete() {
        this.used = Used.N;
    }
}

