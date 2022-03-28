package com.samin.dosan.domain.file;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@MappedSuperclass
public class Files extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(nullable = false)
    protected String originFilename;

    @Column(nullable = false)
    protected String storeFileName;

    protected String contentType;

    protected String extension;

    protected Long fileSize;
}
