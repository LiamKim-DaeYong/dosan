package com.samin.dosan.domain.homepage.gallery.dto;

import com.samin.dosan.domain.homepage.gallery.Gallery;
import com.samin.dosan.domain.homepage.gallery.file.GalleryFile;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class GalleryRequest {

    private Long id;

    private Long previewId;

    private int hit;

    private String author;

    private String title;

    private String content;

    private String contentVal;

    private LocalDate regDt;

    private List<Long> imgIdList;

    public Gallery toEntity() {
        Gallery gallery = Gallery.builder()
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
            gallery.addContentSystem(gallery.getContent());
        }

        if (imgIdList != null) {
            for (int i = 0; i < imgIdList.size(); i++) {
                GalleryFileRequest request = new GalleryFileRequest();
                request.setFileId(imgIdList.get(i));

                if (i == 0) {
                    request.setIsPreview("Y");
                    gallery.setPreviewId(imgIdList.get(i));
                } else {
                    request.setIsPreview("N");
                }

                gallery.addChild(request.toEntity());
            }
        }

        return gallery;
    }

    @Data
    public static class GalleryFileRequest {

        private Long id;

        private Gallery gallery;

        private Long fileId;

        private String isPreview;

        private LocalDate regDt;

        public GalleryFile toEntity() {
            return GalleryFile.builder()
                    .id(this.id)
                    .gallery(this.gallery)
                    .fileId(this.fileId)
                    .isPreview(this.isPreview)
                    .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                    .build();
        }
    }
}
