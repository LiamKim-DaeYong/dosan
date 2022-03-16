package com.samin.dosan.domain.homepage.impression.dto;

import com.samin.dosan.domain.homepage.impression.Impression;
import com.samin.dosan.domain.homepage.impression.file.ImpressionFile;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ImpressionRequest {

    private Long id;

    private int hit;

    private String secret;

    private String password;

    private String author;

    private String title;

    private String content;

    private String contentVal;

    private LocalDate regDt;

    private List<Long> imgIdList;

    private List<Long> fileIdList;

    public Impression toEntity() {
        Impression impression = Impression.builder()
                .id(this.id)
                .hit(hit)
                .secret(this.secret != null ? this.secret : "N")
                .password(this.secret.equals("Y") ? this.password : null)
                .author(this.author)
                .title(this.title)
                .content(this.content)
                .contentVal(this.contentVal)
                .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                .impressionFileList(new ArrayList<>())
                .build();

        if (imgIdList != null) {
            imgIdList.forEach(fileId -> {
                ImpressionFileRequest request = new ImpressionFileRequest();
                request.setFileId(fileId);

                impression.addImpressionFile(request.toEntity("N"));
            });
        }

        if (fileIdList != null) {
            fileIdList.forEach(fileId -> {
                ImpressionFileRequest request = new ImpressionFileRequest();
                request.setFileId(fileId);

                impression.addImpressionFile(request.toEntity("Y"));
            });
        }

        return impression;
    }

    @Data
    public static class ImpressionFileRequest {

        private Long id;

        private Impression impression;

        private Long fileId;

        private String isFile;

        private LocalDate regDt;

        public ImpressionFile toEntity(String isFile) {
            return ImpressionFile.builder()
                    .id(this.id)
                    .impression(this.impression)
                    .fileId(this.fileId)
                    .isFile(isFile)
                    .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                    .build();
        }
    }
}
