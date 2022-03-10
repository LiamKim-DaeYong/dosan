package com.samin.dosan.domain.setting.course;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String subject;

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private CourseType courseType;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public Course init(String type) {
        this.courseType = CourseType.valueOf(type.toUpperCase());
        this.used = Used.Y;

        return this;
    }

    public void update(Course updateData) {
        this.subject = updateData.getSubject();
        this.content = updateData.getContent();
    }

    public void delete() {
        this.used = Used.N;
    }
}
