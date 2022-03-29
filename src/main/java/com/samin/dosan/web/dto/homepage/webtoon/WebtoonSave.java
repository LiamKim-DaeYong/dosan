package com.samin.dosan.web.dto.homepage.webtoon;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class WebtoonSave {

    private Long id;

    @NotBlank
    private String title;

    @NotNull
    private MultipartFile pdf;

    private MultipartFile img;
}
