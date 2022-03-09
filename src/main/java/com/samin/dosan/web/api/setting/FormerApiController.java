package com.samin.dosan.web.api.setting;

import com.samin.dosan.domain.setting.former.Former;
import com.samin.dosan.domain.setting.former.FormerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/setting/former")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class FormerApiController {

    private final FormerService formerService;

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Former saveData) {
        formerService.save(saveData.init());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity edit(@Valid @RequestBody Former updateData){
        formerService.update(updateData.getId(), updateData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        formerService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
