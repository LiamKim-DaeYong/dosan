package com.samin.dosan.web.controller.educator.training_archive;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training_archive.operational.Operational;
import com.samin.dosan.domain.training_archive.operational.OperationalService;
import com.samin.dosan.web.dto.training_archive.OperationalSave;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/educator/training-archive/operational")
// 수련운영자료
public class OperationalController {

    private final OperationalService operationalService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<Operational> result = operationalService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "/educator/training_archive/operational/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute Operational operational) {
        return "/educator/training_archive/operational/addView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("operational", operationalService.findById(id));
        return "/educator/training_archive/operational/detailView";
    }

    @GetMapping("{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        model.addAttribute("operational", operationalService.findById(id));
        return "/educator/training_archive/operational/editView";
    }
}
