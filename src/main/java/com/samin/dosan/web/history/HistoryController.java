package com.samin.dosan.web.history;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/history")
public class HistoryController {

    @GetMapping("/login")
    public String login() {
        return "history/loginList";
    }

    @GetMapping("/data")
    public String data() {
        return "history/dataList";
    }
}
