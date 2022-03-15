package com.samin.dosan.web.controller;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.history.History;
import com.samin.dosan.domain.history.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/history")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public String history(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<History> result = historyService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "history";
    }
}
