package com.samin.dosan.web.api.admin.setting;

import com.samin.dosan.domain.setting.former_code.FormerCode;
import com.samin.dosan.domain.setting.former_code.FormerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/setting/former")
public class FormerApiController {

    private final FormerService formerService;

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody FormerCode saveData) {
        formerService.save(saveData.init());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity edit(@Valid @RequestBody FormerCode updateData){
        formerService.update(updateData.getId(), updateData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        formerService.delete(ids);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/duplicate")
    public ResponseEntity<Boolean> valid(@RequestBody FormerCode validData) {
        return ResponseEntity.ok(formerService.valid(validData));
    }
}
