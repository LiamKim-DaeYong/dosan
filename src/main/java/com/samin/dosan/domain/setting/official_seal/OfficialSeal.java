package com.samin.dosan.domain.setting.official_seal;

import com.samin.dosan.domain.file.Files;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OfficialSeal extends Files {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "official_seal_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private OfficialSealType officialSealType;

    /*================== Business Logic ==================*/
    public void overrideFile(String originFilename) {
//        this.originFilename = originFilename;
    }
}
