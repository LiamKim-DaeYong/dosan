package com.samin.dosan.web.api.admin.board;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileUtil {
    @Value("${file.dir}")
    public static String STORAGE_PATH;

    static final String SEPARATOR = "_";

    /**
     * 디렉토리들 생성
     * <p>
     * 경로상에 위치하는 중간 디렉토리를 모두 생성하며,
     * 이미 존재하는 경우 무시된다.
     * 생성할 수 없는 경우 예외 발생
     *
     * @param dirPath
     */
    public static void makePath(Path dirPath) {
        if (!Files.exists(dirPath, LinkOption.NOFOLLOW_LINKS)) {
//            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxr--r--");
//            FileAttribute<Set<PosixFilePermission>> fileAttributes = PosixFilePermissions.asFileAttribute(permissions);
            try {
//                Files.createDirectories(dirPath, fileAttributes);
                Files.createDirectories(dirPath);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * 파일 저장
     *
     * @param multipartFile 멀티파트 파일
     * @param subDir        서브 디렉토리명
     * @return 파일 정보
     */
    public static FileDto saveFile(MultipartFile multipartFile, String subDir) {

        final UUID uuid = UUID.randomUUID();
        String storageName = multipartFile.getOriginalFilename() + SEPARATOR + uuid;

        Path dirPath = (subDir == null) ? Path.of(STORAGE_PATH) : Path.of(STORAGE_PATH, subDir);
        makePath(dirPath);
        Path filePath = Path.of(dirPath.toString(), storageName);
        try {
            multipartFile.transferTo(filePath);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        String originalName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        Long byteSize = multipartFile.getSize();

        FileDto fileDto = FileDto.builder()
                .uuid(uuid.toString())
                .originalName(originalName)
                .storageName(storageName)
                .subDir(subDir)
                .contentType(contentType)
                .byteSize(byteSize)
                .build();

        return fileDto;
    }

    public static List<FileDto> saveFiles(List<MultipartFile> multipartFiles, String subDir) {
        return multipartFiles.stream()
                .map(multipartFile -> saveFile(multipartFile, subDir))
                .collect(Collectors.toList());
    }

    /**
     * 파일 삭제
     *
     * @param subDir      서브 디렉토리명
     * @param storageName 저장된 파일 이름
     */
    public static void delete(String subDir, String storageName) {
        Path path = (subDir != null)
                ? Path.of(STORAGE_PATH, subDir, storageName)
                : Path.of(STORAGE_PATH, storageName);
        try {
            Files.deleteIfExists(path);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 파일 다운로드 응답
     *
     * @param subDir       저장된 파일이 있는 서브 디렉토리
     * @param storageName  저장된 파일 이름
     * @param originalName 웹브라우저상에 표시될 기본 저장 파일 이름
     * @return 파일 응답
     */
    public static ResponseEntity<Resource> download(String subDir, String storageName, String originalName) {
        Path path = (subDir != null)
                ? Path.of(STORAGE_PATH, subDir, storageName)
                : Path.of(STORAGE_PATH, storageName);
        if (Files.notExists(path)) {
            FileNotFoundException causeException = new FileNotFoundException(path.toString());
            throw new RuntimeException(causeException);
        }

        try {
            Resource resource = new FileUrlResource(path.toString());
            String encordedFilename = URLEncoder.encode(originalName, StandardCharsets.UTF_8).replace("+", "%20");
            String attachmentHeader = String.format("attachment; filename=\"%s\"; filename*=UTF-8''%s", encordedFilename, encordedFilename);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, attachmentHeader)
                    .body(resource);
        } catch (java.net.MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void preview(HttpServletResponse response, FileDto fileDto) {
        MediaType mediaType = null;
        String originalFileName = fileDto.getOriginalName();
        int pos = originalFileName.lastIndexOf(".") + 1;
        String extension = originalFileName.substring(pos).toUpperCase();

        switch (extension) {
            case "JPEG":
            case "JPG":
                mediaType = MediaType.IMAGE_JPEG;
                break;

            case "PNG":
                mediaType = MediaType.IMAGE_PNG;
                break;

            default:
        }

        byte[] bytes;
        try {
            bytes = FileUtils.readFileToByteArray(new File(fileDto.getUrl()));
            response.setContentType(mediaType.toString());
            response.setContentLength(bytes.length);
            IOUtils.copy(FileUtils.openInputStream(new File(fileDto.getUrl())), response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
