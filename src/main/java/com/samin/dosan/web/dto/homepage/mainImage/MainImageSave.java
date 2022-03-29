package com.samin.dosan.web.dto.homepage.mainImage;

import com.samin.dosan.domain.homepage.type.PostType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class MainImageSave {

    @NotBlank
    private String title;

    private PostType postYn;

    private Integer sort = 0;

    @NotNull
    private MultipartFile files;
}
