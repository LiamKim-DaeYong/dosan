package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.main_image.MainImageService;
import com.samin.dosan.web.dto.homepage.mainImage.MainImageSave;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/main-image")
public class MainImageApiController {

    private final MainImageService mainImageService;

    @PostMapping("/add")
    public ResponseEntity save(@Valid MainImageSave saveData) {
        Long id = mainImageService.save(saveData);
        return ResponseEntity.ok(id);
    }

    @PutMapping("{id}/edit")
    public ResponseEntity edit(@PathVariable Long id, @Valid MainImageSave mainImageSave) {
        mainImageService.update(id, mainImageSave);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        mainImageService.delete(ids);
        return ResponseEntity.ok().build();
    }
}