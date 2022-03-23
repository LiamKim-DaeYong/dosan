package com.samin.dosan.web.controller.educator;

import com.samin.dosan.domain.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("educatorBoard")
@RequiredArgsConstructor
@RequestMapping("/educator/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String mainView() {
        return "/educator/board/mainView";
    }

    @GetMapping("/detail")
    public String detailView() {
        return "/educator/board/detailView";
    }

    @GetMapping("/add")
    public String addView() {
        return "/educator/board/addView";
    }

    @GetMapping("/edit")
    public String editView() {
        return "/educator/board/editView";
    }
}
