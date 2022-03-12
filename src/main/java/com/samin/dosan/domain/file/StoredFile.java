package com.samin.dosan.domain.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class StoredFile {

    private String originFilename;

    private String storeFileName;
}
