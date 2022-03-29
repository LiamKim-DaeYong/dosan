package com.samin.dosan.web.dto.homepage.popup;

import com.samin.dosan.domain.homepage.type.DateSetType;
import com.samin.dosan.domain.homepage.type.PostType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class PopupSave {

    private Long id;

    @NotBlank
    private String title;

    private String link;

    private PostType postYn;

    private DateSetType dateSet;

    private String postStart;

    private String postEnd;

    @NotNull
    private MultipartFile files;
}
