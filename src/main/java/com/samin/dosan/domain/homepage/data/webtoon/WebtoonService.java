package com.samin.dosan.domain.homepage.data.webtoon;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.data.webtoon.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;

    public Page<Webtoon> findAll(SearchParam searchParam, Pageable pageable) {
        return webtoonRepository.findAll(searchParam, pageable);
    }

    @Transactional
    public Long save(Webtoon webtoon) {
        return webtoonRepository.save(webtoon).getId();
    }
}
