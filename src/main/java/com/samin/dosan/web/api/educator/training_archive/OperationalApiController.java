package com.samin.dosan.web.api.educator.training_archive;

import com.samin.dosan.domain.training_archive.operational.Operational;
import com.samin.dosan.domain.training_archive.operational.OperationalService;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.web.dto.training_archive.OperationalSave;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/educator/training_archive/operational")
public class OperationalApiController {

    private final OperationalService operationalService;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity save(@Valid @RequestBody OperationalSave saveData) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        saveData.setUser(userService.findById(username));

        Long id = operationalService.save(Operational.of(saveData));

        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody OperationalSave updateData) {
        operationalService.update(id, updateData);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        operationalService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
