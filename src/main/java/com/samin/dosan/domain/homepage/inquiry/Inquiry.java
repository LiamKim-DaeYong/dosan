package com.samin.dosan.domain.homepage.inquiry;

import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.domain.homepage.inquiry.comment.InquiryComment;
import com.samin.dosan.domain.homepage.inquiry.file.InquiryFile;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@Table(name = "homepage_inquiry")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int hit;

    @Column(length = 1)
    private String secret;

    @Column(length = 255)
    private String password;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(columnDefinition="TEXT", nullable = false)
    private String content;

    @Column(columnDefinition="TEXT", nullable = false)
    private String contentVal;

    @Column(columnDefinition="TEXT", nullable = false)
    private String contentSystem;

    @Column(nullable = false)
    private LocalDate regDt;

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InquiryFile> inquiryFileList = new ArrayList<>();

    @OneToOne(mappedBy = "inquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private InquiryComment inquiryComment;

    /*================== Business Logic ==================*/
    public void addInquiryFile(InquiryFile inquiryFile) {
        inquiryFileList.add(inquiryFile);
        inquiryFile.setParent(this);
    }

    public void addInquiryComment(InquiryComment inquiryComment) {
        this.inquiryComment = inquiryComment;
        inquiryComment.setParent(this);
    }

    public void deleteInquiryComment() {
        this.inquiryComment = null;
    }
}
