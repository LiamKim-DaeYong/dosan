package com.samin.dosan.web.controller.admin;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.domain.board.Board;
import com.samin.dosan.domain.board.BoardFile;
import com.samin.dosan.domain.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<Board> result = boardService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/board/mainView";
    }

    @GetMapping("/{id}")
    public String detailView(@PathVariable Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);

        return "admin/board/detailView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute Board board) {
        return "admin/board/addView";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);

        return "admin/board/editView";
    }

    @GetMapping("/attach/{boardId}/{fileId}")
    public ResponseEntity download(@PathVariable Long boardId, @PathVariable Long fileId) throws MalformedURLException {
        Board board = boardService.findById(boardId);

        BoardFile boardFile = board.getFiles().stream()
                .filter(file -> file.getId().equals(fileId)).findFirst().get();

        String originFilename = boardFile.getOriginFilename();
        String storedFileName = boardFile.getStoreFileName();

        return FileUtils.downloadFile(originFilename, storedFileName);
    }
}
