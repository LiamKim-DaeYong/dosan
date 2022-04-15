package com.samin.dosan.web.api.employee.training.program.entry;

import com.samin.dosan.domain.training.program.entry.EntryProgramService;
import com.samin.dosan.domain.training.program.entry.entity.EntryProgram;
import com.samin.dosan.web.dto.training.program.entry.CreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee/training/program/entry")
public class EntryProgramApiController {

    private final EntryProgramService entryProgramService;

    @PostMapping("/{id}/createForm")
    public ResponseEntity createForm(@PathVariable Long id, @RequestBody CreateForm createForm) {
        entryProgramService.createdForm(id, createForm);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody EntryProgram entryProgram) {
        entryProgramService.update(id, entryProgram);
        return ResponseEntity.ok().build();
    }
}
