package com.samin.dosan.domain.setting.place_code;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.core.utils.StrUtils;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "place_code_id")
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String placeNm;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private PlaceCodeType placeCodeType;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static PlaceCode of(PlaceCode saveData, String type) {
        PlaceCode placeCode = new PlaceCode();
        placeCode.placeNm = saveData.getPlaceNm();
        placeCode.placeCodeType = PlaceCodeType.valueOf(StrUtils.urlToEnumName(type));
        placeCode.used = Used.Y;

        return placeCode;
    }

    public void update(PlaceCode placeCodeData) {
        this.placeNm = placeCodeData.getPlaceNm();
    }

    public void delete() {
        this.used = Used.N;
    }
}

