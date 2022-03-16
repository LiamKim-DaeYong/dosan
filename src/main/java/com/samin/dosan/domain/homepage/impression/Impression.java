package com.samin.dosan.domain.homepage.impression;

import com.samin.dosan.domain.homepage.impression.file.ImpressionFile;
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
@Table(name = "homepage_impression")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Impression {

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

    @OneToMany(mappedBy = "impression", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ImpressionFile> impressionFileList;

    public void addImpressionFile(ImpressionFile impressionFile) {
        impressionFileList.add(impressionFile);
        impressionFile.setParent(this);
    }
}
