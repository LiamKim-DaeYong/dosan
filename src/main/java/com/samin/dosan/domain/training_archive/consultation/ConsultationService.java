package com.samin.dosan.domain.training_archive.consultation;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training_archive.consultation.repository.ConsultationRepository;
import com.samin.dosan.web.dto.training_archive.ConsultationSave;
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
public class ConsultationService {

    private final ConsultationRepository consultationArchiveRepository;

    public Page<Consultation> findAll(SearchParam searchParam, Pageable pageable) {
        return consultationArchiveRepository.findAll(searchParam, pageable);
    }

    public Consultation findById(Long id) {
        return consultationArchiveRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(Consultation saveData) {
        return consultationArchiveRepository.save(saveData).getId();
    }

    @Transactional
    public Long update(Long id, ConsultationSave updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
