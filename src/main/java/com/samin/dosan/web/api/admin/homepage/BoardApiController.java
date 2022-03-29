package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.homepage.type.BoardType;
import com.samin.dosan.domain.homepage.board.HomepageBoard;
import com.samin.dosan.domain.homepage.board.HomepageBoardService;
import com.samin.dosan.web.dto.homepage.board.HomepageBoardSave;
import com.samin.dosan.web.dto.homepage.board.HomepageBoardUpdate;
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
    public ResponseEntity save(@Valid HomepageBoardSave saveData, @PathVariable String type) {
        BoardType boardType = BoardType.valueOf(StrUtils.urlToEnumName(type));
        Long id = homepageBoardService.save(saveData, boardType);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity update(@PathVariable Long id, @Valid HomepageBoardUpdate updateData) {
        homepageBoardService.update(id, updateData);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        homepageBoardService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
