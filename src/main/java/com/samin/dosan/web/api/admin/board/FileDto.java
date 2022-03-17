package com.samin.dosan.web.api.admin.board;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDto {
    private Long id;

    /**
     * 파일 식별ID
     */
    private String uuid;

    /**
     * 원본 파일 이름 및 확장자
     */
    private String originalName;

    /**
     * 저장 파일 이름 및 확장자
     */
    private String storageName;

    /**
     * 2차 디렉토리
     */
    private String subDir;

    /**
     * MIME 타입
     */
    private String contentType;

    /**
     * 파일 크기(byte)
     */
    private Long byteSize;

    /**
     * 파일 경로
     */
    private String url;
}
