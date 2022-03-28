package com.samin.dosan.web.api.admin;

import com.samin.dosan.domain.board.BoardService;
import com.samin.dosan.web.dto.board.BoardSave;
import com.samin.dosan.web.dto.board.BoardUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/board")
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/add")
    public ResponseEntity save(@Valid BoardSave saveData) {
        Long id = boardService.save(saveData);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity update(@PathVariable Long id, @Valid BoardUpdate updateData) {
        boardService.update(id, updateData);
        return ResponseEntity.ok(id);
    }
}
