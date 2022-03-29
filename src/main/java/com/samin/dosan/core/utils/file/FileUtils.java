package com.samin.dosan.core.utils.file;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
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

    public static ResponseEntity downloadFile(String originFilename, String storedFileName) throws MalformedURLException {
        UrlResource resource = new UrlResource("file:" + getFullPath(storedFileName));

        String encodedUploadFileName = UriUtils.encode(originFilename, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    public static void deleteFile(String storedFileName) {
        File file = new File(getFullPath(storedFileName));

        if (file.exists()) {
            file.delete();
        }
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

    private static String realOriginalFilename(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(0, pos);
    }

    public static UploadFile getPdfFirstPage(String originalPdfName, String storedPdfName) {
        if (storedPdfName.isBlank()) {
            return null;
        }

        UploadFile uploadFile = null;
        try {
            String pdfPath = getFullPath(storedPdfName);
            File pdfFile = new File(pdfPath);
            PDDocument document = PDDocument.load(pdfFile);
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            String originalImgName = "첫번째 페이지_"+realOriginalFilename(originalPdfName)+".jpg";
            String storeImgName=  createStoreFileName(originalImgName);

            BufferedImage image = pdfRenderer.renderImageWithDPI(0, 100, ImageType.RGB);
            ImageIOUtil.writeImage(image, getFullPath(storeImgName), 300);

            uploadFile = new UploadFile();
            uploadFile.setOriginalFilename(originalImgName);
            uploadFile.setStoreFileName(storeImgName);
            uploadFile.setContentType("image/jpeg");
            uploadFile.setExtension(extractExt(originalImgName));
            uploadFile.setFileSize(null);

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uploadFile;
    }
}
