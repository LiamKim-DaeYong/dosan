package com.samin.dosan.domain.notice;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Page<Notice> findAll(SearchParam searchParam, Pageable pageable) {
//        return noticeRepository.findAll(searchParam, pageable);
        return null;
    }

    public Notice findById(Long id) {
//        return noticeRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("공지사항을 찾을 수 없습니다."));
        return null;
    }

    @Transactional
    public Long updateNotice(Notice notice, Long id) {
        Notice findNotice = findById(id);
        findNotice.updateNotice(notice);

        return save(findNotice);
    }

    @Transactional
    public Long save(Notice notice) {
//        return noticeRepository.save(notice).getId();
        return null;
    }
}
