package com.samin.dosan.domain.homepage.gallery.dto;

import com.samin.dosan.domain.homepage.gallery.Gallery;
import com.samin.dosan.domain.homepage.gallery.file.GalleryFile;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GalleryPreviewResponse {

    private Long id;

    private int hit;

    private String author;

    private String title;

    private String content;

    private String contentVal;

    private LocalDate regDt;

    private Long previewId = null;

    List<GalleryResponse.GalleryFileResponse> childList;

    public GalleryPreviewResponse(Gallery gallery) {
        this.id = gallery.getId();
        this.hit = gallery.getHit();
        this.author = gallery.getAuthor();
        this.title = gallery.getTitle();
        this.content = gallery.getContent();
        this.contentVal = gallery.getContentVal();
        this.regDt = gallery.getRegDt();

        this.childList = gallery.getChildList() != null
                ? gallery.getChildList().stream().map(galleryFile -> new GalleryResponse.GalleryFileResponse(galleryFile))
                .collect(Collectors.toList())
                : new ArrayList<>();

        if (this.childList.size() > 0) {
            Long fileId = null;
            for (GalleryResponse.GalleryFileResponse galleryFile : this.childList) {
                if (galleryFile.getIsPreview().equals("Y")) {
                    fileId = galleryFile.getFileId();
                }
            }

            this.previewId = fileId;
        }
    }

    @Data
    public static class GalleryFileResponse {
        private Long id;

        private Gallery gallery;

        private Long fileId;

        private String isPreview;

        public GalleryFileResponse(GalleryFile galleryFile) {
            this.id = galleryFile.getId();
            this.gallery = galleryFile.getGallery();
            this.fileId = galleryFile.getFileId();
            this.isPreview = galleryFile.getIsPreview();
        }
    }
}
