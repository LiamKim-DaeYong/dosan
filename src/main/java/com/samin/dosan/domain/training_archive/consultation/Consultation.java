package com.samin.dosan.domain.training_archive.consultation;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.web.dto.training_archive.ConsultationSave;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@Table(name = "consultation_archive")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Consultation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate regDt;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static Consultation of(ConsultationSave saveData) {
        Consultation consultation = new Consultation();
        consultation.title = saveData.getTitle();
        consultation.content = saveData.getContent();

        return consultation;
    }

    public void update(ConsultationSave updateData) {
        this.title = updateData.getTitle();
        this.content = updateData.getContent();
    }

    public void delete() {
        this.used = Used.N;
    }
}
