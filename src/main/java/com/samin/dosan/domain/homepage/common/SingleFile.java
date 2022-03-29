package com.samin.dosan.domain.homepage.common;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public class SingleFile extends BaseEntity {

    @Column(nullable = false)
    protected String originFileName;

    @Column(nullable = false)
    protected String storeFileName;

    protected String contentType;

    protected String extension;

    protected Long fileSize;
}
