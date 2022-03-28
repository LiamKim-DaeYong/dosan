package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.board.HomepageBoard;
import com.samin.dosan.domain.homepage.board.HomepageBoardService;
import com.samin.dosan.domain.homepage.type.BoardType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("homepageBoardApiController")
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/board/{type}")
public class BoardApiController {

    private final HomepageBoardService homepageBoardService;

    @PostMapping("/add")
    public ResponseEntity save(@Valid @RequestBody HomepageBoard homepageBoard,
                               @PathVariable String type) {
        BoardType boardType = BoardType.valueOf(type.toUpperCase().replace("-", "_"));
        Long id = homepageBoardService.save(HomepageBoard.of(homepageBoard, boardType));
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity edit(@PathVariable Long id, @Valid @RequestBody HomepageBoard homepageBoard) {
        homepageBoardService.update(id, homepageBoard);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        homepageBoardService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
