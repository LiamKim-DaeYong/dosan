package com.samin.dosan.web.controller.employee.training.program;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training.program.entry.entity.EntryProgram;
import com.samin.dosan.domain.training.program.entry.EntryProgramService;
import com.samin.dosan.web.dto.training.program.entry.EntryProgramList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee/training/program/entry")
public class EntryProgramController {

    private final EntryProgramService entryProgramService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<EntryProgramList> result = entryProgramService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "employee/training/program/entry/mainView";
    }

    @GetMapping("/{id}")
    public String detailView(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        EntryProgram entryProgram = entryProgramService.findById(id);

        if (entryProgram.getCreatedForm() == Boolean.FALSE) {
            redirectAttributes.addAttribute("id", id);
            return "redirect:/employee/training/program/entry/{id}/edit";
        }

        model.addAttribute("entryProgram", entryProgram);

        return "employee/training/program/entry/detailView";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        EntryProgram entryProgram = entryProgramService.findById(id);
        model.addAttribute("entryProgram", entryProgram);

        return "employee/training/program/entry/editView";
    }
}
