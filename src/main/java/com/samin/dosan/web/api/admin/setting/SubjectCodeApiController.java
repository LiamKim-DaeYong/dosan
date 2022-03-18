package com.samin.dosan.web.api.admin.setting;

import com.samin.dosan.domain.setting.subject_code.SubjectCode;
import com.samin.dosan.domain.setting.subject_code.SubjectCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/setting/subject-code/{type}")
public class SubjectCodeApiController {

    private final SubjectCodeService subjectCodeService;

    @PostMapping
    public ResponseEntity save(@PathVariable String type, @Valid @RequestBody SubjectCode saveData) {
        subjectCodeService.save(SubjectCode.of(saveData, type));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@Valid @RequestBody SubjectCode updateData) {
        subjectCodeService.update(updateData.getId(), updateData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        subjectCodeService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
