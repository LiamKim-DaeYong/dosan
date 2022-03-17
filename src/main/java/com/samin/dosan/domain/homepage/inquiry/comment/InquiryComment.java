package com.samin.dosan.domain.homepage.inquiry.comment;

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
@Table(name = "homepage_inquiry_comment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class InquiryComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(columnDefinition="TEXT", nullable = false)
    private String comment;

    @Column(nullable = false)
    private LocalDate regDt;

    public void setParent(Inquiry inquiry) {
        this.inquiry = inquiry;
    }
}
