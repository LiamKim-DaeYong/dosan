package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.advice.AdviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/advice")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdviceApiController {

    private final AdviceService adviceService;

    @PutMapping("/check")
    public ResponseEntity check(@RequestBody Long id) {
        adviceService.check(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        adviceService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
