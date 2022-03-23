package com.samin.dosan.domain.homepage.media_archive.promotional_video;

import com.samin.dosan.core.code.Used;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@Table(name = "homepage_promotional_video")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PromotionalVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String code;

    @Column(length = 20, nullable = false)
    private String author;

    @Column(nullable = false)
    private LocalDate regDt;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static PromotionalVideo of(PromotionalVideo saveData) {
        PromotionalVideo promotionalVideo = new PromotionalVideo();
        promotionalVideo.title = saveData.getTitle();
        promotionalVideo.code = saveData.getCode();
        promotionalVideo.author = "도산선비 문화수련원";
        promotionalVideo.regDt = LocalDate.now();
        promotionalVideo.used = Used.Y;

        return promotionalVideo;
    }

    public void update(PromotionalVideo updateData) {
        this.title = updateData.getTitle();
        this.code = updateData.getCode();
    }

    public void delete() {
        this.used = Used.N;
    }
}
