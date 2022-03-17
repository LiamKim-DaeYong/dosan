package com.samin.dosan.domain.homepage.commonfile;

import com.querydsl.core.BooleanBuilder;
import com.samin.dosan.core.homepage_core.BaseService;
import com.samin.dosan.domain.homepage.commonfile.dto.CommonFileResponse;
import com.samin.dosan.domain.homepage.commonfile.repository.CommonFileRepository;
import com.samin.dosan.domain.homepage.mainImage.MainImageService;
import com.samin.dosan.domain.homepage.popup.PopupService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommonFileService extends BaseService<CommonFile, Long> {

    private String uploadPath = "/var/dosan";

    private final CommonFileRepository commonFileRepository;
    private final MainImageService mainImageService;
    private final PopupService popupService;

    public CommonFileService(CommonFileRepository commonFileRepository,
                             MainImageService mainImageService,
                             PopupService popupService) {
        super(commonFileRepository);
        this.commonFileRepository = commonFileRepository;
        this.mainImageService = mainImageService;
        this.popupService = popupService;
    }

    public CommonFileResponse getFile(Long fileId) {
        CommonFile commonFile = commonFileRepository.findById(fileId).get();
        return new CommonFileResponse(commonFile);
    }

    public List<CommonFileResponse> getFiles(List<Long> fileIdList) {
        List<CommonFile> commonFileList = new ArrayList<>();

        if (fileIdList != null) {
            for (Long id : fileIdList) {
                BooleanBuilder builder = new BooleanBuilder();
                builder.and(qCommonFile.id.eq(id));

                CommonFile commonFile = select().from(qCommonFile).where(builder).fetchOne();
                commonFileList.add(commonFile);
            }
        }

        return commonFileList.stream().map(commonFile -> new CommonFileResponse(commonFile))
                .collect(Collectors.toList());
    }

    public String getFileName(Long fileId) {
        String filename = commonFileRepository.findById(fileId).get().getOrgFilename();
        return filename;
    }

    public Long fileUpload(MultipartFile multipartFile) {
        Long id = null;

        try {
            String originalName = multipartFile.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf(".")+1);
            String filename = UUID.randomUUID()+"_"+originalName;
            Long fileSize = multipartFile.getSize();

            if (extension.equalsIgnoreCase("PDF")) {
                filename = originalName;
            }

            if (!new File(uploadPath).exists()) {
                new File(uploadPath).mkdirs();
            }

            String subPath = calcPath(uploadPath);
            String fullPath = uploadPath + subPath;

            File target = new File(fullPath, filename);
            FileCopyUtils.copy(multipartFile.getBytes(), target);

            CommonFile commonFile = CommonFile.builder()
                    .orgFilename(originalName)
                    .filename(filename)
                    .filePath(fullPath + "/" + filename)
                    .extension(extension)
                    .fileSize(fileSize)
                    .regDt(LocalDate.now())
                    .build();

            id = saveFile(commonFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public List<Long> pdfUpload(MultipartFile[] multipartFileList) {
        List<Long> list = new ArrayList<>();
        try {
            Long pdfId = null;
            Long imgId = null;

            for (MultipartFile multipartFile : multipartFileList) {
                //pdf 저장
                String originalName = multipartFile.getOriginalFilename();
                String extension = originalName.substring(originalName.lastIndexOf(".")+1);

                if (extension.equalsIgnoreCase("pdf")) {
                    pdfId = fileUpload(multipartFile);
                }

                //pdf 첫번째 이미지 저장
                CommonFile commonFile = commonFileRepository.findById(pdfId).get();
                String path = commonFile.getFilePath();

                File pdfFile = new File(path);
                PDDocument document = PDDocument.load(pdfFile);
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                if (!new File(uploadPath).exists()) {
                    new File(uploadPath).mkdirs();
                }

                String pdfName = commonFile.getFilename().substring(0, commonFile.getFilename().lastIndexOf("."));
                String imgFilename = UUID.randomUUID()+"_"+pdfName+"_PAGE1.jpg";
                String subPath = calcPath(uploadPath);
                String fullPath = uploadPath + subPath + "/" + imgFilename;

                BufferedImage imageObj = pdfRenderer.renderImageWithDPI(0, 100, ImageType.RGB);
                ImageIOUtil.writeImage(imageObj, fullPath, 300);
                Path thumbnailPath = Paths.get(fullPath);

                CommonFile commonFileImg = CommonFile.builder()
                        .orgFilename(imgFilename)
                        .filename(pdfName+"_PAGE1.jpg")
                        .filePath(fullPath)
                        .extension("jpg")
                        .fileSize(Files.size(thumbnailPath))
                        .regDt(LocalDate.now())
                        .build();

                imgId = save(commonFileImg).getId();

                document.close();

                list.add(pdfId);
                list.add(imgId);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return list;
    }

    public void download(HttpServletRequest request, HttpServletResponse response, Long fileId) {
        FileInputStream fis = null;
        OutputStream out = null;

        try {
            BooleanBuilder builder = new BooleanBuilder();
            builder.and(qCommonFile.id.eq(fileId));
            CommonFile dto = select().from(qCommonFile).where(builder).fetchOne();
            String filePath = dto.getFilePath();

            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                String browser = getBrowser(request);
                String disposition = getDisposition(dto.getOrgFilename(), browser);


                response.setContentLength((int) file.length());
                response.setHeader("Content-Description", "JSP Generated Data");
                response.setHeader("Content-Transfer-Encoding", "binary");
                response.setContentType("application/octet-stream; charset=UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + disposition);

                out = response.getOutputStream();
                fis = new FileInputStream(file);
                FileCopyUtils.copy(fis, out);
                out.flush();

            }
        } catch (Exception e) {
            response.setContentType("text/html; charset=UTF-8");
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) { fis.close(); }
                if (out != null) { out.close(); }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openPdf(HttpServletResponse response, Long fileId) {
        try {
            BooleanBuilder builder = new BooleanBuilder();
            builder.and(qCommonFile.id.eq(fileId));
            CommonFile commonFile = select().from(qCommonFile).where(builder).fetchOne();
            String filePath = commonFile.getFilePath();
            String filename = commonFile.getOrgFilename();

            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                byte[] fileByte = FileUtils.readFileToByteArray(file);

                response.setContentType("application/pdf; name="+filename);
                response.setContentLength(fileByte.length);

                response.encodeRedirectURL(filePath);
                response.setHeader("Content-Disposition", "inline; fileName=\"" + filename + "\";");
                response.getOutputStream().write(fileByte);

                response.getOutputStream().flush();
                response.getOutputStream().close();
                System.out.println(response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            response.setContentType("text/html; charset=UTF-8");
            e.printStackTrace();
        }
    }

    private String calcPath(String uploadPath) {
        Calendar cal = Calendar.getInstance();
        String yearPath = File.separator + cal.get(Calendar.YEAR);

        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

        String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);

        return datePath.replaceAll("\\\\", "/");
    }

    private void makeDir(String uploadPath, String... paths) {
        if (new File(paths[paths.length - 1]).exists()) {
            return;
        }

        for (String path : paths) {
            File dirPath = new File(uploadPath + path);

            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        }
    }

    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1)
            return "MSIE";
        else if (header.indexOf("Chrome") > -1)
            return "Chrome";
        else if (header.indexOf("Opera") > -1)
            return "Opera";
        return "Firefox";
    }

    private String getDisposition(String filename, String browser)
            throws UnsupportedEncodingException {
        String encodedFilename = null;
        if (browser.equals("MSIE")) {
            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll(
                    "\\+", "%20");
        } else if (browser.equals("Firefox")) {
            encodedFilename = "\""
                    + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Opera")) {
            encodedFilename = "\""
                    + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Chrome")) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < filename.length(); i++) {
                char c = filename.charAt(i);
                if (c > '~') {
                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
                } else {
                    sb.append(c);
                }
            }
            encodedFilename = sb.toString();
        }
        return encodedFilename;
    }

    public void preview(HttpServletResponse response, Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCommonFile.id.eq(id));
        CommonFile commonFile = select().from(qCommonFile).where(builder).fetchOne();

        if (commonFile == null)
            return;

        MediaType mediaType = null;

        String extension = commonFile.getExtension().toUpperCase().toString();
        switch (extension) {
            case "JPEG":
            case "JPG":
                mediaType = MediaType.IMAGE_JPEG;
                break;

            case "PNG":
                mediaType = MediaType.IMAGE_PNG;
                break;

            case "GIF":
                mediaType = MediaType.IMAGE_GIF;
                break;

            default:
        }

        byte[] bytes;
        try {
            bytes = FileUtils.readFileToByteArray(new File(commonFile.getFilePath()));
            response.setContentType(mediaType.toString());
            response.setContentLength(bytes.length);
            IOUtils.copy(FileUtils.openInputStream(new File(commonFile.getFilePath())), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public boolean checkWidthAndHeightSize(MultipartFile multipartFile, String width, String height, String pageName) {
//        boolean result = true;
//
//        switch (pageName) {
//            case "mainImage" :
//                result = mainImageService.mainImageInputValidation(multipartFile, width, height);
//                break;
//
//            case "popup" :
//                result = popupService.popupImageInputValidation(multipartFile, width, height);
//                break;
//
//            default :
//        }
//
//
//        return result;
//    }

    @Transactional
    public Long saveFile(CommonFile commonFile) {
        return commonFileRepository.save(commonFile).getId();
    }

    @Transactional
    public void deleteFile(Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCommonFile.id.eq(id));
        CommonFile commonFile = select().from(qCommonFile).where(builder).fetchOne();

        delete(commonFile);
    }
}
