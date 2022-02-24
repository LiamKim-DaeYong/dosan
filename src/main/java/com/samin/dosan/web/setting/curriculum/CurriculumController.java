package com.samin.dosan.web.setting.curriculum;

import com.samin.dosan.domain.setting.curriculum.Curriculum;
import com.samin.dosan.domain.setting.curriculum.CurriculumService;
import com.samin.dosan.domain.type.CurriculumType;
import com.samin.dosan.web.param.SearchParam;
import com.samin.dosan.web.setting.curriculum.dto.CurriculumData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/setting/curriculum")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CurriculumController {

    private final CurriculumService curriculumService;

    @ModelAttribute("curriculumTypes")
    public CurriculumType[] curriculumTypes() {
        return CurriculumType.values();
    }

    @GetMapping
    public String list(SearchParam searchParam, Model model) {
//        List<Curriculum> curriculumList = curriculumService.findAll(searchParam);
//
//        CurriculumDto curriculum = new CurriculumDto(curriculumList, curriculumType);
//        model.addAttribute("curriculum", curriculum);

        return "setting/curriculum/curriculumList";
    }

    @GetMapping("/{curriculumType}/add")
    public String addForm(@PathVariable String curriculumType, CurriculumData curriculumData) {
        curriculumData.setCurriculumType(curriculumType);
        return "setting/curriculum/curriculumAddForm";
    }

    @PostMapping("/add")
    public String save(@Valid CurriculumData curriculumData, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("modal-error", true);
            redirectAttributes.addAttribute("curriculumType", true);
            return "setting/curriculum/curriculumList";
        }

        curriculumService.save(curriculumData.toEntity());
        redirectAttributes.addAttribute("type", curriculumData.getCurriculumType());
        return "redirect:/setting/curriculum/{type}";
    }

//    @PostConstruct
//    public void init() {
//        Curriculum curriculum = Curriculum.builder()
//                .subject("강의")
//                .content("선비정신과 퇴계선생")
//                .curriculumType(CurriculumType.ENTRY)
//                .build();
//
//        curriculumService.save(curriculum);
//
//        Curriculum curriculum2 = Curriculum.builder()
//                .subject("탁본체험")
//                .content("퇴계선생의 좌우명 배우기")
//                .curriculumType(CurriculumType.SCHOOL)
//                .build();
//
//        curriculumService.save(curriculum2);
//    }
}
