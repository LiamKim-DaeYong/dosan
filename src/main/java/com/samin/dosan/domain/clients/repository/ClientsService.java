package com.samin.dosan.domain.clients.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.clients.Clients;
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
public class ClientsService {

    private final ClientsRepository clientsRepository;

    public Page<Clients> findAll(SearchParam searchParam, Pageable pageable) {
        return clientsRepository.findAll(searchParam, pageable);
    }

    public Clients findById(Long id) {
        return clientsRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(Clients clients) {
        return clientsRepository.save(clients).getId();
    }

    @Transactional
    public Long update(Long id, Clients updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
