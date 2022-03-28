package com.samin.dosan.domain.training_archive.lesson;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.web.dto.training_archive.LessonSave;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "lesson_archive")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lesson extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GradeType gradeType;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static Lesson of(LessonSave saveData) {
        Lesson lesson = new Lesson();
        lesson.user = saveData.getUser();
        lesson.used = Used.Y;

        lesson.gradeType = GradeType.valueOf(StrUtils.urlToEnumName(saveData.getGradeType()));
        lesson.title = saveData.getTitle();
        lesson.content = saveData.getContent();

        return lesson;
    }

    public void update(LessonSave updateData) {
        this.gradeType = GradeType.valueOf(StrUtils.urlToEnumName(updateData.getGradeType()));
        this.title = updateData.getTitle();
        this.content = updateData.getContent();
    }

    public void delete() {
        this.used = Used.N;
    }
}
