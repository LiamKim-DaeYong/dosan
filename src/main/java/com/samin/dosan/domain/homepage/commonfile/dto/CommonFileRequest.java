package com.samin.dosan.domain.homepage.commonfile.dto;

import com.samin.dosan.domain.homepage.commonfile.CommonFile;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CommonFileRequest {

    private Long id;

    private Long fileSize;

    private String orgFilename;

    private String filename;

    private String filePath;

    private String extension;

    private LocalDate regDt = LocalDate.now();

    public CommonFile toEntity() {
        return CommonFile.builder()
                .id(this.id)
                .fileSize(this.fileSize)
                .orgFilename(this.orgFilename)
                .filename(this.filename)
                .filePath(this.filePath)
                .extension(this.extension)
                .regDt(this.regDt)
                .build();
    }
}
