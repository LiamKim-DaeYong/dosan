package com.samin.dosan.web.board.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BoardSaveForm {

    @NotEmpty
    private String title;

    @NotBlank
    private String content;
}
