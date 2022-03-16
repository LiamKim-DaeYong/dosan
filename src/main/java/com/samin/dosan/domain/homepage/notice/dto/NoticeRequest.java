package com.samin.dosan.domain.homepage.notice.dto;

import com.samin.dosan.domain.homepage.notice.Homepage_Notice;
import com.samin.dosan.domain.homepage.notice.file.NoticeFile;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class NoticeRequest {

    private Long id;

    private int hit;

    private String author;

    private String title;

    private String content;

    private String contentVal;

    private LocalDate regDt;

    private List<Long> imgIdList;

    private List<Long> fileIdList;

    public Homepage_Notice toEntity() {
        Homepage_Notice notice = Homepage_Notice.builder()
                .id(this.id)
                .hit(this.hit)
                .author(this.author)
                .title(this.title)
                .content(this.content)
                .contentVal(this.contentVal)
                .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                .childList(new ArrayList<>())
                .build();

        if (content != null) {
            notice.addContentSystem(notice.getContent());
        }

        if (imgIdList != null) {
            imgIdList.forEach(fileId -> {
                NoticeFileRequest request = new NoticeFileRequest();
                request.setFileId(fileId);

                notice.addChild(request.toEntity("N"));
            });
        }

        if (fileIdList != null) {
            fileIdList.forEach(fileId -> {
                NoticeFileRequest request = new NoticeFileRequest();
                request.setFileId(fileId);

                notice.addChild(request.toEntity("Y"));
            });
        }

        return notice;
    }

    @Data
    public static class NoticeFileRequest {

        private Long id;

        private Homepage_Notice notice;

        private Long fileId;

        private String isFile;

        private LocalDate regDt;

        public NoticeFile toEntity(String isFile) {
            return NoticeFile.builder()
                    .id(this.id)
                    .notice(this.notice)
                    .fileId(fileId)
                    .isFile(isFile)
                    .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                    .build();
        }
    }
}
