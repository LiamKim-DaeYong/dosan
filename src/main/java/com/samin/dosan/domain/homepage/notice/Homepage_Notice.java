package com.samin.dosan.domain.homepage.notice;

import com.samin.dosan.domain.homepage.notice.file.NoticeFile;
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
@Table(name = "homepage_notice")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Homepage_Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @Column(nullable = false)
    private int hit;

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

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoticeFile> childList;

    public void addContentSystem(String content) {
        String contentSystem = content;
        if (content.contains("/admin/homepage/file/preview")) {
            contentSystem = contentSystem.replace("/admin/homepage/file/preview", "/file/preview");
        }

        this.contentSystem = contentSystem;
    }

    public List<NoticeFile> getChildList() {
        return childList;
    }

    public void addChild(NoticeFile noticeFile) {
        childList.add(noticeFile);
        noticeFile.setParent(this);
    }
}
