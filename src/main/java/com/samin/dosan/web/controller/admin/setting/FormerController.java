package com.samin.dosan.web.controller.admin.setting;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.former.Former;
import com.samin.dosan.domain.setting.former.FormerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/admin/setting/former")
public class FormerController {

    private final FormerService formerService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable,
                            Model model) {
        Page<Former> result = formerService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/setting/former/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute Former former) {
        return "admin/setting/former/addView::#form";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        Former former = formerService.findById(id);
        model.addAttribute("former", former);

        return "admin/setting/former/editView::#form";
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
