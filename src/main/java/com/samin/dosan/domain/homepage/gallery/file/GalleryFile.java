package com.samin.dosan.domain.homepage.gallery.file;

import com.samin.dosan.domain.homepage.gallery.Gallery;
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
@Table(name = "homepage_gallery_file")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class GalleryFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Gallery gallery;

    @Column(nullable = false)
    private Long fileId;

    @Column(nullable = false)
    private String isPreview;

    @Column(nullable = false)
    private LocalDate regDt;

    public void setParent(Gallery gallery) {
        this.gallery = gallery;
    }
}
