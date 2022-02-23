package com.samin.dosan.web.setting.curriculum;

import com.samin.dosan.domain.setting.curriculum.Curriculum;
import com.samin.dosan.domain.setting.curriculum.CurriculumService;
import com.samin.dosan.domain.type.CurriculumType;
import com.samin.dosan.web.dto.SearchParam;
import com.samin.dosan.web.setting.curriculum.dto.CurriculumDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/setting/curriculum")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CurriculumController {

    private final CurriculumService curriculumService;
    private final ModelMapper modelMapper;

    @GetMapping("/{type}")
    public String list(@PathVariable String type, SearchParam searchParam, Model model) {
        List<CurriculumDto> curriculums = curriculumService.findAll(type, searchParam)
                .stream().map(curriculum -> new CurriculumDto(curriculum))
                .collect(Collectors.toList());

        model.addAttribute("curriculums", curriculums);
        model.addAttribute("type", type);

        return "setting/curriculum/curriculumList";
    }

    @PostMapping("/test")
    public String test(Model model) {
        model.addAttribute("type", "entry");
        model.addAttribute("test", "test");
        return "setting/curriculum/curriculumList :: modal";
    }

    @PostConstruct
    public void init() {
        Curriculum curriculum = Curriculum.builder()
                .subject("강의")
                .content("선비정신과 퇴계선생")
                .curriculumType(CurriculumType.ENTRY)
                .build();

        curriculumService.save(curriculum);

        Curriculum curriculum2 = Curriculum.builder()
                .subject("탁본체험")
                .content("퇴계선생의 좌우명 배우기")
                .curriculumType(CurriculumType.SCHOOL)
                .build();

        curriculumService.save(curriculum2);
    }
}
