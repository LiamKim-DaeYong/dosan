package com.samin.dosan.domain.notice;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<Notice> findAll(SearchParam searchParam) {
        return noticeRepository.findAll(searchParam);
    }

    public Notice findById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("공지사항을 찾을 수 없습니다."));
    }

    @Transactional
    public Long updateNotice(Notice notice, Long id) {
        Notice findNotice = findById(id);
        findNotice.updateNotice(notice);

        return save(findNotice);
    }

    @Transactional
    public Long save(Notice notice) {
        return noticeRepository.save(notice).getId();
    }
}