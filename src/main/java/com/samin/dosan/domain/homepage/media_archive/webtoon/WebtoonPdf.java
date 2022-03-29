package com.samin.dosan.domain.homepage.media_archive.webtoon;

import com.samin.dosan.domain.homepage.common.SingleFile;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public class WebtoonPdf extends SingleFile {

    @Column(nullable = false)
    protected String originPdfName;

    @Column(nullable = false)
    protected String storePdfName;

    protected String pdfContentType;

    protected String pdfExtension;

    protected Long pdfSize;
}
