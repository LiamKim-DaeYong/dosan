package com.samin.dosan.web.controller.educator.training_archive;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training_archive.operational.Operational;
import com.samin.dosan.domain.training_archive.operational.OperationalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/educator/training_archive/operational")
// 수련운영자료
public class OperationalController {

    private final OperationalService operationalService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<Operational> result = operationalService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "/educator/training_archive/operational/mainView";
    }

    @GetMapping("/{id}il")
    public String detailView() {
        return "/educator/training_archive/operational/detailView";
    }

    @GetMapping("/add")
    public String addView() {
        return "/educator/training_archive/operational/addView";
    }

    @GetMapping("/edit")
    public String editView() {
        return "/educator/training_archive/operational/editView";
    }
}
