package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.homepage.type.DateSetType;
import com.samin.dosan.domain.homepage.type.PostType;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.popup.Popup;
import com.samin.dosan.domain.homepage.popup.PopupService;
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
@RequestMapping("/admin/homepage/popup")
public class PopupController {

    private final PopupService popupService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam,
                        Pageable pageable, Model model) {
        Page<Popup> result = popupService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/homepage/popup/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute Popup popup, Model model) {
        model.addAttribute("popupPostYnTypes", PostType.values());

        return "admin/homepage/popup/addView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("popup", popupService.findById(id));

        return "admin/homepage/popup/detailView";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        model.addAttribute("popupPostYnTypes", PostType.values());
        model.addAttribute("popup", popupService.findById(id));

        return "admin/homepage/popup/editView";
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i < 1001; i++) {
            Popup popup = Popup.builder()
                    .postYn(PostType.Y)
                    .dateSet(DateSetType.Y)
                    .postStart("2022-03-20")
                    .postEnd("2022-03-22")
                    .title("제목"+i)
                    .link(null)
                    .originFilename("12312231.jpg")
                    .storeFileName("23241142.jpg")
                    .regDt(LocalDate.now())
                    .used(Used.Y)
                    .build();

            popupService.save(popup);
        }
    }
}
