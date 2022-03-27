package com.samin.dosan.domain.file;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
