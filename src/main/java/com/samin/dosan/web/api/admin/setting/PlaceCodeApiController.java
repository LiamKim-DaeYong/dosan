package com.samin.dosan.web.api.admin.setting;

import com.samin.dosan.domain.setting.place_code.PlaceCode;
import com.samin.dosan.domain.setting.place_code.PlaceCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/setting/place-code/{type}")
public class PlaceCodeApiController {

    private final PlaceCodeService placeCodeService;

    @PostMapping
    public ResponseEntity save(@PathVariable String type, @Valid @RequestBody PlaceCode saveData) {
        placeCodeService.save(PlaceCode.of(saveData, type));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@Valid @RequestBody PlaceCode updateData) {
        placeCodeService.update(updateData.getId(), updateData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        placeCodeService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
