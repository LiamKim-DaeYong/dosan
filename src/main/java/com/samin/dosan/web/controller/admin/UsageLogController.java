package com.samin.dosan.web.controller.admin;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.usagelog.UsageLog;
import com.samin.dosan.domain.usagelog.UsageLogService;
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
@RequestMapping("/admin/usage-log")
public class UsageLogController {

    private final UsageLogService usageLogService;

    @GetMapping
    public String history(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<UsageLog> result = usageLogService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/usageLog";
    }
}
