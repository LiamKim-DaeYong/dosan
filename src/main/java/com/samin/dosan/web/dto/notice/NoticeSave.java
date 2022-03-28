package com.samin.dosan.web.dto.notice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class NoticeSave {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    List<MultipartFile> files = new ArrayList<>();
}
