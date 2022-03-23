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
        return ResponseEntity.ok(promotionalVideoService.save(PromotionalVideo.of(saveData)));
    }

    @PutMapping("/promotional-video/{id}/edit")
    public ResponseEntity edit(@PathVariable Long id, @Valid @RequestBody PromotionalVideo updateData) {
        return ResponseEntity.ok(promotionalVideoService.update(id, updateData));
    }

    @PostMapping("/webtoon/add")
    public ResponseEntity save(@Valid WebtoonSave saveData) {
        return ResponseEntity.ok(webtoonService.save(Webtoon.of(saveData)));
    }

    @PostMapping("/webtoon/{id}/edit")
    public ResponseEntity edit(@PathVariable Long id, @Valid WebtoonSave updateData) {
        return ResponseEntity.ok(webtoonService.update(id, updateData));
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        promotionalVideoService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
