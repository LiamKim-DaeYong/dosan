package com.samin.dosan.web.board;

import com.samin.dosan.domain.board.Board;
import com.samin.dosan.domain.board.BoardService;
import com.samin.dosan.web.board.dto.BoardDto;
import com.samin.dosan.web.board.dto.BoardSaveForm;
import com.samin.dosan.web.param.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

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
    public String addForm(@ModelAttribute("board") BoardSaveForm boardSaveForm) {
        return "board/boardAddForm";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(@Valid @ModelAttribute("board") BoardSaveForm boardSaveForm, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "board/boardAddForm";
        }

        Board board = Board.builder()
                .title(boardSaveForm.getTitle())
                .content(boardSaveForm.getContent())
                .build();

        redirectAttributes.addAttribute("id", boardService.save(board));
        redirectAttributes.addAttribute("test", 1);

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
}
