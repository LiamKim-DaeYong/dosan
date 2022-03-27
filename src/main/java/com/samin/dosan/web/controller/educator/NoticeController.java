package com.samin.dosan.web.controller.educator;

import com.samin.dosan.domain.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("educatorNotice")
@RequiredArgsConstructor
@RequestMapping("/educator/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public String mainView() {
        return "/educator/notice/mainView";
    }

    @GetMapping("/{id}il")
    public String detailView() {
        return "/educator/notice/detailView";
    }

    @GetMapping("/add")
    public String addView() {
        return "/educator/notice/addView";
    }

    @GetMapping("/edit")
    public String editView() {
        return "/educator/notice/editView";
    }
}
