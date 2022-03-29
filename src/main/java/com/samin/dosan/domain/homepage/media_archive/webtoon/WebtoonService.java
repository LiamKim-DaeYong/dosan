package com.samin.dosan.domain.homepage.media_archive.webtoon;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.homepage.media_archive.webtoon.repository.WebtoonRepository;
import com.samin.dosan.web.dto.homepage.webtoon.WebtoonSave;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;

    public Page<Webtoon> findAll(SearchParam searchParam, Pageable pageable) {
        return webtoonRepository.findAll(searchParam, pageable);
    }

    public Webtoon findById(Long id) {
        return webtoonRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(WebtoonSave saveData) {
        Webtoon webtoon = Webtoon.of(saveData);

        try {
            UploadFile uploadPdf = FileUtils.fileUpload(saveData.getPdf());
            webtoon.addPdf(uploadPdf);

            String imgName = saveData.getImg().getOriginalFilename();
            UploadFile uploadImg;
            if (imgName.isBlank()) {
                uploadImg = FileUtils.getPdfFirstPage(webtoon.getOriginPdfName(), webtoon.getStorePdfName());
            } else {
                uploadImg = FileUtils.fileUpload(saveData.getImg());
            }

            webtoon.addImg(uploadImg);

            webtoonRepository.save(webtoon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return webtoon.getId();
    }

    @Transactional
    public Long update(Long id, WebtoonSave updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
