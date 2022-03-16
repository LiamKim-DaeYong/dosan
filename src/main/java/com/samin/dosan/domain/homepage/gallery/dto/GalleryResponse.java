package com.samin.dosan.domain.homepage.gallery.dto;

import com.samin.dosan.domain.homepage.gallery.Gallery;
import com.samin.dosan.domain.homepage.gallery.file.GalleryFile;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GalleryResponse {

    private Long id;

    private Long previewId;

    private int hit;

    private String author;

    private String title;

    private String content;

    private String contentVal;

    private LocalDate regDt;

    List<GalleryFileResponse> childList;

    public GalleryResponse(Gallery gallery) {
        this.id = gallery.getId();
        this.previewId = gallery.getPreviewId();
        this.hit = gallery.getHit();
        this.author = gallery.getAuthor();
        this.title = gallery.getTitle();
        this.content = gallery.getContent();
        this.contentVal = gallery.getContentVal();
        this.regDt = gallery.getRegDt();
        this.childList = gallery.getChildList() != null
                ? gallery.getChildList().stream().map(galleryFile -> new GalleryFileResponse(galleryFile))
                .collect(Collectors.toList())
                : new ArrayList<>();
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
