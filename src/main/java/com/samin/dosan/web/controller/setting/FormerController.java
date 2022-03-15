package com.samin.dosan.web.controller.setting;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.former.Former;
import com.samin.dosan.domain.setting.former.FormerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/former")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class FormerController {

    private final FormerService formerService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable,
                            Model model) {
        Page<Former> result = formerService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "setting/former/former";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute Former former) {
        return "setting/former/addForm::#form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Former former = formerService.findById(id);
        model.addAttribute("former", former);

        return "setting/former/editForm::#form";
    }

    @PostConstruct
    public void init() {
        Former former = Former.builder()
                .formerName("초등교원")
                .used(Used.Y)
                .build();

        formerService.save(former);
    }
}
