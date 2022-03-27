package com.samin.dosan.core.utils.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileUtils {

    private static String fileDir;

    @Value("${file.dir}")
    public void setFileDir(String value) {
        fileDir = value;
    }


    public static UploadFile fileUpload(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        UploadFile uploadFile = new UploadFile();
        uploadFile.setOriginalFilename(originalFilename);
        uploadFile.setStoreFileName(storeFileName);
        uploadFile.setContentType(multipartFile.getContentType());
        uploadFile.setExtension(extractExt(originalFilename));
        uploadFile.setFileSize(multipartFile.getSize());

        return uploadFile;
    }

    private static String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private static String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    public static String getFullPath(String filename) {
        return fileDir + filename;
    }
}
