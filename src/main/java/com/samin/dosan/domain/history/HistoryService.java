package com.samin.dosan.domain.history;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryService {

    private final HistoryRepository historyRepository;

    public Page<History> findAll(SearchParam searchParam, Pageable pageable) {
        return historyRepository.findAll(searchParam, pageable);
    }

    @Transactional
    public void save(History history) {
        historyRepository.save(history);
    }
}
