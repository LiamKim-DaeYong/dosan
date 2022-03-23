package com.samin.dosan.web.dto.homepage.popup;

import com.samin.dosan.core.code.homepage.DateSetType;
import com.samin.dosan.core.code.homepage.PostType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
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
    private MultipartFile file;
}
