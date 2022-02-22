package com.samin.dosan.web.setting.crclm;

import com.samin.dosan.domain.setting.crclm.CurriculumType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/setting/crclm")
public class CurriculumController {

    @GetMapping
    public String crclmPage(@ModelAttribute("type") String type) {
        if (type == null) {
            type = CurriculumType.ENTRY.toString();
        }

        log.info("type={}", type);

        return "setting/crclm/curriculums";
    }
}
