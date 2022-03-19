package com.samin.dosan.web.api.admin.setting;

import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/setting/former-job-code")
public class FormerJobCodeApiController {

    private final FormerJobCodeService formerJobCodeService;

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody FormerJobCode saveData) {
        formerJobCodeService.save(saveData.of(saveData));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @Valid @RequestBody FormerJobCode updateData){
        formerJobCodeService.update(id, updateData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        formerJobCodeService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
