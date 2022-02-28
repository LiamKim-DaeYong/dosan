package com.samin.dosan.web.setting.course;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CourseForm {

    private Long id;

    @NotBlank
    private String subject;

    @NotBlank
    private String content;

    private String type;
}
