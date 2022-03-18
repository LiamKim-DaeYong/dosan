package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.domain.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("homepageBoardController")
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/board/{type}")
public class BoardController {

    private final NoticeService noticeService;
}
