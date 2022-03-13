package com.samin.dosan.web.controller;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.board.Board;
import com.samin.dosan.domain.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String boards(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<Board> result = boardService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "board/boards";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute Board board) {
        return "board/addForm";
    }
}
