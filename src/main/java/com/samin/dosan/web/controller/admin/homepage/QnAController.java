package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.homepage.type.SecretType;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.qna.Qna;
import com.samin.dosan.domain.homepage.qna.QnaService;
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
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/qna")
public class QnAController {

    private final QnaService qnaService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        Page<Qna> result = qnaService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/homepage/qna/mainView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("qna", qnaService.findById(id));

        return "admin/homepage/qna/detailView";
    }
}
