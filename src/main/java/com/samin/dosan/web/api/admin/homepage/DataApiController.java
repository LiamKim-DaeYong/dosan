package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.data.promotion.Promotion;
import com.samin.dosan.domain.homepage.data.promotion.PromotionService;
import com.samin.dosan.domain.homepage.data.webtoon.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
public class DataApiController {

    private final PromotionService promotionService;
    private final WebtoonService webtoonService;

    @PostMapping("/promotion/add")
    public ResponseEntity save(@Valid @RequestBody Promotion saveData) {
        return ResponseEntity.ok(promotionService.save(saveData.init()));
    }

    @PutMapping("/promotion/{id}/edit")
    public ResponseEntity edit(@Valid @RequestBody Promotion updateData) {
        return ResponseEntity.ok(promotionService.update(updateData.getId(), updateData));
    }

    @DeleteMapping("/promotion")
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        promotionService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
