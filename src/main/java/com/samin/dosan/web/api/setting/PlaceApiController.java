package com.samin.dosan.web.api.setting;

import com.samin.dosan.domain.setting.place.Place;
import com.samin.dosan.domain.setting.place.PlaceService;
import com.samin.dosan.domain.setting.place.PlaceType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place/{type}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PlaceApiController {

    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity save(@PathVariable String type, @Valid @RequestBody Place saveData) {
        placeService.save(saveData.init(type));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity edit(@Valid @RequestBody Place updateData) {
        placeService.update(updateData.getId(), updateData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        placeService.delete(ids);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/duplicate")
    public ResponseEntity<Boolean> valid(@PathVariable String type, @RequestBody Place validData) {
        PlaceType placeType = PlaceType.valueOf(type.toUpperCase());
        return ResponseEntity.ok(placeService.valid(placeType, validData));
    }
}
