package com.samin.dosan.web.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping
    public String list() {
        return "board/boardList";
    }

    @GetMapping("/admin/add")
    public String addForm() {
        return "board/boardAddForm";
    }
}
