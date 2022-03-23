package com.samin.dosan.web.dto.homepage.webtoon;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WebtoonSave {

    private Long id;

    @NotBlank
    private String title;

    @NotNull
    private MultipartFile pdf;

    @NotNull
    private MultipartFile img;
}
