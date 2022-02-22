package com.samin.dosan.web.setting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/setting/seal")
public class SealMngController {

    @Value("${file.dir}")
    private String fileDir;


    @GetMapping
    public String sealMngPage(MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            String fullPath = fileDir + file.getOriginalFilename();
            file.transferTo(new File(fullPath));
        }

        return "setting/sealMng";
    }
}
