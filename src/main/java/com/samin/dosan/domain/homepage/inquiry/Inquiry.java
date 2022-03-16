package com.samin.dosan.domain.homepage.inquiry;

import com.samin.dosan.domain.homepage.inquiry.comment.InquiryComment;
import com.samin.dosan.domain.homepage.inquiry.file.InquiryFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Table(name = "homepage_inquiry")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
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
    List<InquiryFile> inquiryFileList;

    @OneToOne(mappedBy = "inquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    InquiryComment inquiryComment;

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
