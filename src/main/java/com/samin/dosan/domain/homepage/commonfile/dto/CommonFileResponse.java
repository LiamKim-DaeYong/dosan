package com.samin.dosan.domain.homepage.commonfile.dto;

import com.samin.dosan.domain.homepage.commonfile.CommonFile;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CommonFileResponse {

    private Long id;

    private Long fileSize;

    private String orgFilename;

    private String filename;

    private String filePath;

    private String extension;

    private LocalDate regDt;

    public CommonFileResponse(CommonFile commonFile) {
        this.id = commonFile.getId();
        this.fileSize = commonFile.getFileSize();
        this.orgFilename = commonFile.getOrgFilename();
        this.filename = commonFile.getFilename();
        this.filePath = commonFile.getFilePath();
        this.extension = commonFile.getExtension();
        this.regDt = commonFile.getRegDt();
    }
}
