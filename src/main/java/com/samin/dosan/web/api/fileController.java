package com.samin.dosan.web.api;

import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.core.utils.file.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class fileController {

    @PostMapping("/edit/upload")
    public ResponseEntity editImgUpload(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        UploadFile uploadFile = FileUtils.fileUpload(multipartFile);
        String fileUrl = "/files/img/" + uploadFile.getStoreFileName();

        return ResponseEntity.ok(fileUrl);
    }

    @GetMapping("/img/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + FileUtils.getFullPath(filename));
    }
}
