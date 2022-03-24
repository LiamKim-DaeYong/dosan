package com.samin.dosan.domain.file;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FilesService {

    @Value("${file.dir}")
    private String fileDir;

    private final FilesRepository filesRepository;

//    public List<StoredFile> saveFile(List<UploadFile> uploadFiles) throws IOException {
//        List<StoredFile> storeFileResult = new ArrayList<>();
//
//        for (UploadFile uploadFile : uploadFiles) {
//            if (!uploadFile.getFile().isEmpty()) {
//                storeFileResult.add(storeFile(uploadFile.getFile()));
//            }
//        }
//
//        return storeFileResult;
//    }
//
//    @Transactional
//    public StoredFile storeFile(MultipartFile multipartFile) throws IOException {
//        if (multipartFile.isEmpty()) {
//            return null;
//        }
//
//        String originalFilename = multipartFile.getOriginalFilename();
//        String storeFileName = createStoreFileName(originalFilename);
//        multipartFile.transferTo(new File(getFullPath(storeFileName)));
//
//        Files file = Files.builder()
//                .originFilename(originalFilename)
//                .storeFileName(storeFileName)
//                .contentType(multipartFile.getContentType())
//                .extension(extractExt(originalFilename))
//                .fileSize(multipartFile.getSize())
//                .build();
//
//        Files storedFile = filesRepository.save(file);
//
//        return new StoredFile(storedFile.getId(), originalFilename, storeFileName);
//    }
//
//    private String createStoreFileName(String originalFilename) {
//        String ext = extractExt(originalFilename);
//        String uuid = UUID.randomUUID().toString();
//        return uuid + "." + ext;
//    }
//
//    private String extractExt(String originalFilename) {
//        int pos = originalFilename.lastIndexOf(".");
//        return originalFilename.substring(pos + 1);
//    }
//
//    public String getFullPath(String filename) {
//        return fileDir + filename;
//    }
}
