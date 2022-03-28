package com.samin.dosan.domain.notice;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.SecurityUtils;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.notice.repository.NoticeRepository;
import com.samin.dosan.domain.user.SessionUser;
import com.samin.dosan.web.dto.notice.NoticeSave;
import com.samin.dosan.web.dto.notice.NoticeUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Page<Notice> findAll(SearchParam searchParam, Pageable pageable) {
        return noticeRepository.findAll(searchParam, pageable);
    }

    public Notice findById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("공지사항을 찾을 수 없습니다."));
    }

    @Transactional
    public Long save(NoticeSave saveData) {
        SessionUser loginUser = SecurityUtils.getLoginUser();
        Notice notice = Notice.of(saveData, loginUser.getUserNm());

        saveData.getFiles().stream().forEach(file -> {
            try {
                UploadFile uploadFile = FileUtils.fileUpload(file);
                notice.addFile(uploadFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        noticeRepository.save(notice);

        return notice.getId();
    }

    @Transactional
    public void update(Long id, NoticeUpdate updateData) {
        Notice notice = findById(id);
        notice.update(updateData);
    }
}
