package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.domain.board.BoardFile;
import com.samin.dosan.domain.homepage.board.HomepageBoard;
import com.samin.dosan.domain.homepage.board.HomepageBoardFile;
import com.samin.dosan.domain.homepage.board.HomepageBoardService;
import com.samin.dosan.domain.homepage.type.BoardType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;

@Controller("homepageBoard")
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/board/{type}")
public class BoardController {

    private final HomepageBoardService homepageBoardService;

    @ModelAttribute("boardTypes")
    public BoardType[] boardTypes() {
        return BoardType.values();
    }

    @ModelAttribute("boardType")
    public BoardType boardType(@PathVariable String type) {
        return BoardType.valueOf(StrUtils.urlToEnumName(type));
    }

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam,
                           @PathVariable String type,
                           Pageable pageable, Model model) {
        BoardType boardType = BoardType.valueOf(StrUtils.urlToEnumName(type));
        Page<HomepageBoard> result = homepageBoardService.findAll(searchParam, pageable, boardType);
        model.addAttribute("result", result);

        return "admin/homepage/board/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute("board") HomepageBoard board) {
        return "admin/homepage/board/addView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("board", homepageBoardService.findById(id));

        return "admin/homepage/board/detailView";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("board", homepageBoardService.findById(id));

        return "admin/homepage/board/editView";
    }

    @GetMapping("/attach/{boardId}/{fileId}")
    public ResponseEntity download(@PathVariable Long boardId, @PathVariable Long fileId) throws MalformedURLException {
        HomepageBoard board = homepageBoardService.findById(boardId);

        HomepageBoardFile boardFile = board.getFiles().stream()
                .filter(file -> file.getId().equals(fileId)).findFirst().get();

        String originFilename = boardFile.getOriginFilename();
        String storedFilename = boardFile.getStoreFileName();

        return FileUtils.downloadFile(originFilename, storedFilename);
    }
}
