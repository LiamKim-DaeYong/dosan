package com.samin.dosan.domain.homepage.notice.file;

import com.samin.dosan.domain.homepage.notice.Homepage_Notice;
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
@Table(name = "homepage_notice_file")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class NoticeFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Homepage_Notice notice;

    @Column(nullable = false)
    private Long fileId;

    @Column(length = 1)
    private String isFile;

    @Column(nullable = false)
    private LocalDate regDt;

    public void setParent(Homepage_Notice notice) {
        this.notice = notice;
    }
}
