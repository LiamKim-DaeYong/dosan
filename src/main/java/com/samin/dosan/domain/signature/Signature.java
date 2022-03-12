package com.samin.dosan.domain.signature;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Signature extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "signature_id")
    private Long id;

    @Column(nullable = false)
    private String originFilename;

    @Column(nullable = false)
    private String storeFileName;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private SignatureType signatureType;

    /*================== Business Logic ==================*/
    public void overrideFile(String originFilename) {
        this.originFilename = originFilename;
    }
}
