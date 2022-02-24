package com.samin.dosan.web.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @GetMapping
    public String notice() {
        return "notice/noticeList";
    }

    @GetMapping("/admin/add")
    public String addForm() {
        return "notice/noticeAddForm";
    }

    @PostMapping("/admin/add")
    public void save() {

    }
}
