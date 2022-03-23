package com.samin.dosan.domain.homepage.popup;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.code.homepage.DateSetType;
import com.samin.dosan.core.code.homepage.PostType;
import com.samin.dosan.web.dto.homepage.popup.PopupSave;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "homepage_popup")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Popup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private PostType postYn;

    @Column(length = 1, nullable = false)
    private DateSetType dateSet;

    @Column(length = 20, nullable = false)
    private String postStart;

    @Column(length = 20, nullable = false)
    private String postEnd;

    @Column(nullable = false)
    private String title;

    @Column
    private String link;

    @Column(nullable = false)
    private String originFilename;

    @Column(nullable = false)
    private String storeFileName;

    @Column(nullable = false)
    private LocalDate regDt;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static Popup of(PopupSave saveData) {
        Popup popup = new Popup();
        popup.regDt = LocalDate.now();
        popup.used = Used.Y;

        popup.title = saveData.getTitle();
        popup.link = saveData.getLink();
        popup.postYn = saveData.getPostYn();
        popup.dateSet = saveData.getDateSet();
        popup.postStart = saveData.getPostStart();
        popup.postEnd = saveData.getPostEnd();

        popup.originFilename = saveData.getFile().getOriginalFilename();
        popup.storeFileName = UUID.randomUUID()+"_"+popup.originFilename;

        return popup;
    }

    public void update(PopupSave updateData) {
        this.title = updateData.getTitle();
        this.link = updateData.getLink();
        this.postYn = updateData.getPostYn();
        this.dateSet = updateData.getDateSet();
        this.postStart = updateData.getPostStart();
        this.postEnd = updateData.getPostEnd();

        this.originFilename = updateData.getFile().getOriginalFilename();
        this.storeFileName = UUID.randomUUID()+"_"+this.originFilename;
    }

    public void delete() {
        this.used = Used.N;
    }
}
