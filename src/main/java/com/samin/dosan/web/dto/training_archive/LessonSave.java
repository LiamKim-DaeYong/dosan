package com.samin.dosan.web.dto.training_archive;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.user.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LessonSave {

    private Long id;

    private User user;

    private String gradeType;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Used used;
}
