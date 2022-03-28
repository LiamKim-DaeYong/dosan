package com.samin.dosan.web.api.educator.training_archive;

import com.samin.dosan.domain.training_archive.consultation.Consultation;
import com.samin.dosan.domain.training_archive.consultation.ConsultationService;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.web.dto.training_archive.ConsultationSave;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/educator/training_archive/consultation")
public class ConsultationApiController {

    private final ConsultationService consultationService;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity save(@Valid @RequestBody ConsultationSave saveData) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        saveData.setUser(userService.findById(username));

        Long id = consultationService.save(Consultation.of(saveData));
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody ConsultationSave updateData) {
        consultationService.update(id, updateData);

        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        consultationService.delete(ids);

        return ResponseEntity.ok().build();
    }
}
