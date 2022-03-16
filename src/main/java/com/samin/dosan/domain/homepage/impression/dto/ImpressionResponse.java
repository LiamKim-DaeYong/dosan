package com.samin.dosan.domain.homepage.impression.dto;

import com.samin.dosan.domain.homepage.impression.Impression;
import com.samin.dosan.domain.homepage.impression.file.ImpressionFile;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ImpressionResponse {

    private Long id;

    private int hit;

    private String secret;

    private String password;

    private String author;

    private String title;

    private String content;

    private String contentVal;

    private String contentSystem;

    private LocalDate regDt;

    private List<ImpressionFileResponse> impressionFileList;

    public ImpressionResponse(Impression impression) {
        this.id = impression.getId();
        this.hit = impression.getHit();
        this.secret = impression.getSecret();
        this.password = impression.getPassword();
        this.author = impression.getAuthor();
        this.title = impression.getTitle();
        this.content = impression.getContent();
        this.contentVal = impression.getContentVal();
        this.contentSystem = impression.getContentSystem();
        this.regDt = impression.getRegDt();
        this.impressionFileList = impression.getImpressionFileList() != null
                ? impression.getImpressionFileList().stream().map(impressionFile -> new ImpressionFileResponse(impressionFile))
                .collect(Collectors.toList())
                : new ArrayList<>();
    }

    @Data
    public static class ImpressionFileResponse {

        private Long id;

        private Long fileId;

        private String isFile;

        public ImpressionFileResponse(ImpressionFile impressionFile) {
            this.id = impressionFile.getId();
            this.fileId = impressionFile.getFileId();
            this.isFile = impressionFile.getIsFile();
        }
    }
}
