package com.samin.dosan.domain.usagelog;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.usagelog.repository.UsageLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsageLogService {

    private final UsageLogRepository usageLogRepository;

    public Page<UsageLog> findAll(SearchParam searchParam, Pageable pageable) {
        return usageLogRepository.findAll(searchParam, pageable);
    }

    @Transactional
    public void save(UsageLog usageLog) {
        usageLogRepository.save(usageLog);
    }
}
