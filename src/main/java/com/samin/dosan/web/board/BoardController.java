package com.samin.dosan.web.board;

import com.samin.dosan.domain.board.Board;
import com.samin.dosan.domain.board.BoardService;
import com.samin.dosan.web.board.dto.BoardDto;
import com.samin.dosan.web.board.dto.BoardSaveForm;
import com.samin.dosan.web.param.SearchParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String list(SearchParam searchParam, Model model) {
        List<BoardDto> boardList = boardService.findAll(searchParam)
                .stream().map(board -> new BoardDto(board))
                .collect(Collectors.toList());

        model.addAttribute("boardList", boardList);

        return "board/boardList";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);

        return "board/boardDetail";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addForm(@ModelAttribute("board") BoardSaveForm board) {
        return "board/boardAddForm";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(@Valid @ModelAttribute("board") BoardSaveForm boardSaveForm, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "board/boardAddForm";
        }

        Board board = modelMapper.map(boardSaveForm, Board.class);
        redirectAttributes.addAttribute("id", boardService.save(board));

        return "redirect:/board/{id}";
    }


    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editForm(@PathVariable Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);

        return "board/boardEditForm";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String edit(Board board) {
        return "board/boardEditForm";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@RequestParam(name = "check", required = false) Long[] ids) {
        log.info("ids={}", ids);
        return "redirect:/board";
    }
}
