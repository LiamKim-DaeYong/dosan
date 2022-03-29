package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.popup.PopupService;
import com.samin.dosan.web.dto.homepage.popup.PopupSave;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/popup")
public class PopupApiController {

    private final PopupService popupService;

    @PostMapping("/add")
    public ResponseEntity save(@Valid PopupSave saveData) {
        Long id = popupService.save(saveData);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity edit(@PathVariable Long id, @Valid PopupSave updateData) {
        popupService.update(id, updateData);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        popupService.delete(ids);
        return ResponseEntity.ok().build();
    }
}