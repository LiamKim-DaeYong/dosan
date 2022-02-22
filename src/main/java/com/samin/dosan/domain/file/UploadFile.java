package com.samin.dosan.domain.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@AllArgsConstructor
public class UploadFile {

    private Long id;

    private MultipartFile file;
}
