package com.samin.dosan.core.utils.file;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UploadFile {

    private String originalFilename;

    private String storeFileName;

    private String contentType;

    private String extension;

    private Long fileSize;
}
