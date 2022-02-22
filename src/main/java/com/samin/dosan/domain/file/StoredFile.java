package com.samin.dosan.domain.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class StoredFile {

    private Long id;

    private String originFilename;

    private String storeFileName;

    private String contentType;


}
