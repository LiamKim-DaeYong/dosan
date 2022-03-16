package com.samin.dosan.web.api.homepage.util;

import com.samin.dosan.domain.homepage.commonfile.CommonFileService;
import com.samin.dosan.domain.homepage.commonfile.dto.CommonFileResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * NOTE : 파일 관리
 * */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/file")
public class FileController {

    private final CommonFileService commonFileService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/get/file/{fileId}")
    public CommonFileResponse getFile(@PathVariable(name = "fileId") Long fileId) {
        CommonFileResponse commonFileResponse = null;

        try {
            commonFileResponse = commonFileService.getFile(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commonFileResponse;
    }

    @PostMapping("/check/widthAndHeight")
    public boolean checkWidthAndHeightSize(@RequestParam("file") MultipartFile multipartFile,
                                           @RequestParam("width") String width,
                                           @RequestParam("height") String height,
                                           @RequestParam("pageName") String pageName) {
        boolean result = commonFileService.checkWidthAndHeightSize(multipartFile, width, height, pageName);

        return result;
    }

    @GetMapping("/preview/{id}")
    public void preview(HttpServletResponse response, @PathVariable("id") Long id) {
        commonFileService.preview(response, id);
    }

    @GetMapping("/download/{fileId}")
    public void download(HttpServletResponse response, HttpServletRequest request, @PathVariable("fileId") Long fileId) {
        try {
            commonFileService.download(request, response, fileId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    @GetMapping("/open/pdf/{fileId}")
    public void openPdf(HttpServletResponse response, @PathVariable("fileId") Long fileId) {
        try {
            commonFileService.openPdf(response, fileId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    @PostMapping("/saveFiles")
    public List<Long> fileSave(@RequestParam("file") MultipartFile[] multipartFileList) {
        List<Long> idList = new ArrayList<>();
        try {
            for (MultipartFile multipartFile : multipartFileList) {
                if (!Objects.equals(multipartFile.getOriginalFilename(), "")) {
                    Long id = commonFileService.fileUpload(multipartFile);
                    idList.add(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return idList;
    }

    @PostMapping("/savePdf")
    public String pdfSave(@RequestParam("file") MultipartFile[] multipartFileList) {
        String strResult = "{ \"result\":\"FAIL\" }";
        List<Long> idList;
        try {
            if (multipartFileList != null) {
                idList = commonFileService.pdfUpload(multipartFileList);
                strResult = "{ \"result\":\"OK\", \"pdfId\" : " + idList.get(0) + ", \"imgId\" : "+ idList.get(1) + " }";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strResult;
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        commonFileService.deleteFile(id);
    }
}
