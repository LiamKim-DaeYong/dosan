package com.samin.dosan.web.controller.educator.training_archive;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.training_archive.operational.Operational;
import com.samin.dosan.domain.training_archive.operational.OperationalService;
import com.samin.dosan.domain.training_archive.operational.OperationalType;
import com.samin.dosan.domain.user.UserService;
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

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
@RequestMapping("/educator/training-archive/operational/{type}")
public class OperationalController {

    private final OperationalService operationalService;
    private final UserService userService;

    @ModelAttribute("operationalTypes")
    public OperationalType[] operationalTypes() {
        return OperationalType.values();
    }

    @ModelAttribute("operationalType")
    public OperationalType operationalType(@PathVariable String type) {
        return OperationalType.valueOf(StrUtils.urlToEnumName(type));
    }

    @GetMapping
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<Operational> result = operationalService.findAll(searchParam, pageable, type);
        model.addAttribute("result", result);

        return "/educator/training_archive/operational/mainView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("operational", operationalService.findById(id));
        return "/educator/training_archive/operational/detailView";
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i < 101; i++) {
            Operational a = Operational.builder()
                    .user(userService.findById("educator"))
                    .used(Used.Y)
                    .operationalType(OperationalType.ENTRY)
                    .title("입교"+i)
                    .content("입교내용"+i)
                    .build();
            operationalService.save(a);

            Operational b = Operational.builder()
                    .user(userService.findById("educator"))
                    .used(Used.Y)
                    .operationalType(OperationalType.SCHOOL)
                    .title("학교"+i)
                    .content("학교내용"+i)
                    .build();

            operationalService.save(b);
        }
    }
}
