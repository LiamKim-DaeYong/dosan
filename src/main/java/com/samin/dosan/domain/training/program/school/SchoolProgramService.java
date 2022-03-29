package com.samin.dosan.domain.training.program.school;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training.program.school.repository.SchoolProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolProgramService {

    private final SchoolProgramRepository schoolProgramRepository;

    public Page<SchoolProgram> findAll(SearchParam searchParam, Pageable pageable) {
        return schoolProgramRepository.findAll(searchParam, pageable);
    }
}
