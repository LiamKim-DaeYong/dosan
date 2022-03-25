package com.samin.dosan.web.dto.training_archive;

import com.samin.dosan.domain.user.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ConsultationSave {

    private Long id;

    private User user;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
