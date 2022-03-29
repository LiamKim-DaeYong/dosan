package com.samin.dosan.domain.homepage.popup;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.homepage.popup.repository.PopupRepository;
import com.samin.dosan.web.dto.homepage.popup.PopupSave;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PopupService {

    private final PopupRepository popupRepository;

    public Page<Popup> findAll(SearchParam searchParam, Pageable pageable) {
        return popupRepository.findAll(searchParam, pageable);
    }

    public Popup findById(Long id) {
        return popupRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("팝업을 찾을 수 없습니다."));
    }

    @Transactional
    public Long save(PopupSave saveData) {
        Popup popup = Popup.of(saveData);

        try {
            UploadFile uploadFile = FileUtils.fileUpload(saveData.getFiles());
            popup.addFile(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        popupRepository.save(popup);

        return popup.getId();
    }

    @Transactional
    public Long update(Long id, PopupSave updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
