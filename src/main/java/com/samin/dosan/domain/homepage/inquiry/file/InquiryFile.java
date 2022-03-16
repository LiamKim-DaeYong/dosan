package com.samin.dosan.domain.homepage.inquiry.file;

import com.samin.dosan.domain.homepage.inquiry.Inquiry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "homepage_inquiry_file")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class InquiryFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Inquiry inquiry;

    @Column(nullable = false)
    private Long fileId;

    @Column(length = 1)
    private String isFile;

    @Column(nullable = false)
    private LocalDate regDt;

    public void setParent(Inquiry inquiry) {
        this.inquiry = inquiry;
    }
}
