package com.samin.dosan.web.api.setting;

import com.samin.dosan.domain.setting.educator.EducatorCode;
import com.samin.dosan.domain.setting.educator.EducatorCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/setting/educator/{type}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class EducatorCodeApiController {

    private final EducatorCodeService educatorCodeService;

    @PostMapping
    public ResponseEntity save(@PathVariable String type, @Valid @RequestBody EducatorCode saveData) {
        educatorCodeService.save(saveData.init(type));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity edit(@Valid @RequestBody EducatorCode updateData) {
        educatorCodeService.update(updateData.getId(), updateData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        educatorCodeService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
