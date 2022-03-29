package com.samin.dosan.domain.homepage.qna;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QnaService {

    private final QnaRepository qnaRepository;

    public List<Qna> findAll() {
        return qnaRepository.findAll();
    }

    public Page<Qna> findAll(SearchParam searchParam, Pageable pageable) {
        return qnaRepository.findAll(searchParam, pageable);
    }

    public Qna findById(Long id) {
        return qnaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(Qna saveData) {
        return qnaRepository.save(saveData).getId();
    }

    @Transactional
    public void comment(Long id, String comment) {
        findById(id).comment(comment);
    }

    @Transactional
    public void commentDelete(Long id) {
        findById(id).commentDelete();
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
