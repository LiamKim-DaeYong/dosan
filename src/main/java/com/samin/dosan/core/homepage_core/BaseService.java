package com.samin.dosan.core.homepage_core;



import com.samin.dosan.domain.homepage.advice.QAdvice;
import com.samin.dosan.domain.homepage.commonfile.QCommonFile;
import com.samin.dosan.domain.homepage.gallery.QGallery;
import com.samin.dosan.domain.homepage.gallery.file.QGalleryFile;
import com.samin.dosan.domain.homepage.impression.QImpression;
import com.samin.dosan.domain.homepage.impression.file.QImpressionFile;
import com.samin.dosan.domain.homepage.inquiry.QInquiry;
import com.samin.dosan.domain.homepage.inquiry.comment.QInquiryComment;
import com.samin.dosan.domain.homepage.inquiry.file.QInquiryFile;
import com.samin.dosan.domain.homepage.mainImage.QMainImage;
import com.samin.dosan.domain.homepage.newsletter.QNewsletter;
import com.samin.dosan.domain.homepage.notice.QHomepage_Notice;
import com.samin.dosan.domain.homepage.notice.file.QNoticeFile;
import com.samin.dosan.domain.homepage.popup.QPopup;
import com.samin.dosan.domain.homepage.promotion.QPromotion;
import com.samin.dosan.domain.homepage.report.QReport;
import com.samin.dosan.domain.homepage.report.file.QReportFile;
import com.samin.dosan.domain.homepage.webtoon.QWebtoon;

import java.io.Serializable;

public class BaseService<T, ID extends Serializable> extends BaseQueryDSLService<T, ID> {

    //homepage
    protected QAdvice qAdvice = QAdvice.advice;
    protected QPromotion qPromotion = QPromotion.promotion;
    protected QWebtoon qWebtoon = QWebtoon.webtoon;
    protected QNewsletter qNewsletter = QNewsletter.newsletter;

    protected QGallery qGallery = QGallery.gallery;
    protected QGalleryFile qgalleryFile = QGalleryFile.galleryFile;

    protected QHomepage_Notice qNotice = QHomepage_Notice.homepage_Notice;
    protected QNoticeFile qNoticeFile = QNoticeFile.noticeFile;

    protected QReport qReport = QReport.report;
    protected QReportFile qReportFile = QReportFile.reportFile;

    protected QInquiry qInquiry = QInquiry.inquiry;
    protected QInquiryFile qInquiryFile = QInquiryFile.inquiryFile;
    protected QInquiryComment qInquiryComment = QInquiryComment.inquiryComment;

    protected QImpression qImpression = QImpression.impression;
    protected QImpressionFile qImpressionFile = QImpressionFile.impressionFile;

    //admin
    protected QMainImage qMainImage = QMainImage.mainImage;
    protected QPopup qPopup = QPopup.popup;

    //util
    protected QCommonFile qCommonFile = QCommonFile.commonFile;

    protected BaseJpaQueryDSLRepository<T, ID> repository;

    public BaseService(BaseJpaQueryDSLRepository<T, ID> repository) {
        super(repository);
        this.repository = repository;
    }
}