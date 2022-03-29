package com.samin.dosan.domain.homepage.main_image;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.homepage.main_image.repository.MainImageRepository;
import com.samin.dosan.web.dto.homepage.mainImage.MainImageSave;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainImageService {

    private final MainImageRepository mainImageRepository;

    public Page<MainImage> findAll(SearchParam searchParam, Pageable pageable) {
        return mainImageRepository.findAll(searchParam, pageable);
    }

    public MainImage findById(Long id) {
        return mainImageRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(MainImageSave saveData) {
        MainImage mainImage = MainImage.of(saveData);

        try {
            UploadFile uploadFile = FileUtils.fileUpload(saveData.getFiles());
            mainImage.addFile(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainImageRepository.save(mainImage);

        return mainImage.getId();
    }

    @Transactional
    public Long update(Long id, MainImageSave updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
