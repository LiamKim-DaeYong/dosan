package com.samin.dosan.domain.file;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Builder
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String originFilename;

    @Column(nullable = false)
    private String storeFileName;

    private String contentType;

    private String extension;

    private Long fileSize;
}
