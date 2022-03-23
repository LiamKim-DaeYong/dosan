package com.samin.dosan.web.api.admin.setting;

import com.samin.dosan.domain.setting.educator_code.EducatorCode;
import com.samin.dosan.domain.setting.educator_code.EducatorCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/setting/educator-code/{type}")
public class EducatorCodeApiController {

    private final EducatorCodeService educatorCodeService;

    @PostMapping
    public ResponseEntity save(@PathVariable String type, @Valid @RequestBody EducatorCode saveData) {
        educatorCodeService.save(EducatorCode.of(saveData, type));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @Valid @RequestBody EducatorCode updateData) {
        educatorCodeService.update(id, updateData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        educatorCodeService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
