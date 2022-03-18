package com.samin.dosan.domain.setting.official_seal;

import com.samin.dosan.domain.setting.official_seal.repository.SignatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignatureService {

    @Value("${file.signature}")
    private String fileDir;

    private final SignatureRepository signatureRepository;

    public OfficialSeal findTypeOfSystem() {
        return signatureRepository.findTypeOfSystem();
    }

    @Transactional
    public void save(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return;
        }

        OfficialSeal typeOfSystem = signatureRepository.findTypeOfSystem();

        if (typeOfSystem != null) {
            typeOfSystem.overrideFile(multipartFile.getOriginalFilename());
            multipartFile.transferTo(new File(getFullPath(typeOfSystem.getStoreFileName())));
        } else {
            String originalFilename = multipartFile.getOriginalFilename();
            String storeFileName = createStoreFileName(originalFilename);
            multipartFile.transferTo(new File(getFullPath(storeFileName)));

//            OfficialSeal officialSeal = OfficialSeal.builder()
//                    .originFilename(originalFilename)
//                    .storeFileName(storeFileName)
//                    .officialSealType(OfficialSealType.SYSTEM)
//                    .build();

//            signatureRepository.save(officialSeal);
        }
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    public String getFullPath(String filename) {
        return fileDir + filename;
    }
}
