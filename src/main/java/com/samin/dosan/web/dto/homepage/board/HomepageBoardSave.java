package com.samin.dosan.web.dto.homepage.board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class HomepageBoardSave {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    List<MultipartFile> files = new ArrayList<>();
}
