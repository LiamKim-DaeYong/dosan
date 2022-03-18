package com.samin.dosan.web.api.admin.setting;

import com.samin.dosan.domain.setting.place_code.PlaceCode;
import com.samin.dosan.domain.setting.place_code.PlaceCodeType;
import com.samin.dosan.domain.setting.place_code.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/setting/place/{type}")
public class PlaceApiController {

    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity save(@PathVariable String type, @Valid @RequestBody PlaceCode saveData) {
        placeService.save(saveData.init(type));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity edit(@Valid @RequestBody PlaceCode updateData) {
        placeService.update(updateData.getId(), updateData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        placeService.delete(ids);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/duplicate")
    public ResponseEntity<Boolean> valid(@PathVariable String type, @RequestBody PlaceCode validData) {
        PlaceCodeType placeCodeType = PlaceCodeType.valueOf(type.toUpperCase());
        return ResponseEntity.ok(placeService.valid(placeCodeType, validData));
    }
}
