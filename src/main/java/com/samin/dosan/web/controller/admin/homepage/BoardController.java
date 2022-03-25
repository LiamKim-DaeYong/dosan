package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.homepage.type.BoardType;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.board.HomepageBoard;
import com.samin.dosan.domain.homepage.board.HomepageBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

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
        return BoardType.valueOf(type.toUpperCase().replace("-", "_"));
    }

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam,
                           @PathVariable String type,
                           Pageable pageable, Model model) {
        BoardType boardType = BoardType.valueOf(type.toUpperCase().replace("-", "_"));
        Page<HomepageBoard> result = homepageBoardService.findAll(searchParam, pageable, boardType);
        model.addAttribute("result", result);

        return "admin/homepage/board/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute("board") HomepageBoard board) {
        return "admin/homepage/board/addView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable("id") Long id, @PathVariable("type") String type,
                             Model model) {
        model.addAttribute("board", homepageBoardService.findById(id));

        return "admin/homepage/board/detailView";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable("id") Long id, @PathVariable("type") String type,
                           Model model) {
        model.addAttribute("board", homepageBoardService.findById(id));

        return "admin/homepage/board/editView";
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i < 1001; i++) {
            HomepageBoard board1 = HomepageBoard.builder()
                    .boardType(BoardType.NOTICE)
                    .hit(0)
                    .author("작성자"+i)
                    .title(BoardType.NOTICE.getDescription() + i)
                    .content(String.valueOf(i))
                    .regDt(LocalDate.now())
                    .used(Used.Y)
                    .build();

            HomepageBoard board2 = HomepageBoard.builder()
                    .boardType(BoardType.REPORTED_NEWS)
                    .hit(0)
                    .author("작성자"+i)
                    .title(BoardType.REPORTED_NEWS.getDescription() + i)
                    .content(String.valueOf(i))
                    .regDt(LocalDate.now())
                    .used(Used.Y)
                    .build();

            HomepageBoard board3 = HomepageBoard.builder()
                    .boardType(BoardType.PHOTO_GALLERY)
                    .hit(0)
                    .author("작성자"+i)
                    .title(BoardType.PHOTO_GALLERY.getDescription() + i)
                    .content(String.valueOf(i))
                    .regDt(LocalDate.now())
                    .used(Used.Y)
                    .build();

            homepageBoardService.save(board1);
            homepageBoardService.save(board2);
            homepageBoardService.save(board3);
        }
    }
}
