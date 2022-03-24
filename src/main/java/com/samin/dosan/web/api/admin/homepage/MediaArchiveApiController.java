package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.media_archive.promotional_video.PromotionalVideo;
import com.samin.dosan.domain.homepage.media_archive.promotional_video.PromotionalVideoService;
import com.samin.dosan.domain.homepage.media_archive.webtoon.Webtoon;
import com.samin.dosan.domain.homepage.media_archive.webtoon.WebtoonService;
import com.samin.dosan.web.dto.homepage.webtoon.WebtoonSave;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/media-archive")
public class MediaArchiveApiController {

    private final PromotionalVideoService promotionalVideoService;
    private final WebtoonService webtoonService;

    @PostMapping("/promotional-video/add")
    public ResponseEntity save(@Valid @RequestBody PromotionalVideo saveData) {
        Long id = promotionalVideoService.save(PromotionalVideo.of(saveData));
        return ResponseEntity.ok(id);
    }

    @PutMapping("/promotional-video/{id}/edit")
    public ResponseEntity edit(@PathVariable Long id, @Valid @RequestBody PromotionalVideo updateData) {
        promotionalVideoService.update(id, updateData);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/webtoon/add")
    public ResponseEntity save(@Valid WebtoonSave saveData) {
        Long id = webtoonService.save(Webtoon.of(saveData));
        return ResponseEntity.ok(id);
    }

    @PostMapping("/webtoon/{id}/edit")
    public ResponseEntity edit(@PathVariable Long id, @Valid WebtoonSave updateData) {
        webtoonService.update(id, updateData);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        promotionalVideoService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
