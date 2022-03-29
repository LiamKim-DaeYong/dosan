package com.samin.dosan.web.dto.training_archive;

import com.samin.dosan.domain.training_archive.operational.OperationalType;
import com.samin.dosan.domain.user.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OperationalSave {

    private Long id;

    private User user;

    private String operationalType;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
