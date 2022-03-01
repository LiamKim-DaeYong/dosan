package com.samin.dosan.domain.setting.course;

import com.samin.dosan.domain.BaseEntity;
import com.samin.dosan.domain.type.setting.CourseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "과목명을 입력해 주세요.")
    @Column(length = 100, nullable = false)
    private String subject;

    @NotBlank(message = "내용을 입력해 주세요.")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    public void setCourseType(String type) {
        this.courseType = CourseType.valueOf(type.toUpperCase());
    }

    public void update(Course updateData) {
        this.subject = updateData.getSubject();
        this.content = updateData.getContent();
    }
}
