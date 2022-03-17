package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.popup.Popup;
import com.samin.dosan.domain.homepage.popup.PopupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * NOTE : 관리자 - 팝업 관리
 * */
@Controller
@RequiredArgsConstructor
@RequestMapping("/popup")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PopupController {

    private final PopupService popupService;

    @GetMapping
    public String popup(@ModelAttribute SearchParam searchParam,
                        Pageable pageable, Model model) {
        Page<Popup> result = popupService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "homepage/popup/list";
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i < 101; i++) {
            Popup popup = Popup.builder()
                    .fileId(1L)
                    .postYn("Y")
                    .dateSet("Y")
                    .postStart(String.valueOf(LocalDate.now()))
                    .postEnd(String.valueOf(LocalDate.now()))
                    .title("제목"+i)
                    .link("www.naver.com")
                    .regDt(LocalDateTime.now())
                    .build();

            popupService.save(popup);
        }
    }
//
//    @PostMapping("/page")
//    public String popupPage(@RequestParam int page, @RequestBody FilterDto filterDto, Model model) {
//        Page<PopupResponse> popupList = popupService.getPopupList_admin(page, filterDto);
//
//        model.addAttribute("page", page);
//        model.addAttribute("filter", filterDto);
//        model.addAttribute("popupList", popupList);
//
//        return "homepage/popup/list :: popupFrag";
//    }
//
//    @GetMapping("/regist")
//    public String popupRegist() {
//        return "homepage/popup/regist";
//    }
//
//    @GetMapping("/detail/{id}")
//    public String popupDetail(@PathVariable("id") Long id, Model model) {
//        PopupResponse response = popupService.getPopup_admin(id);
//
//        model.addAttribute("popup", response);
//
//        return "homepage/popup/detail";
//    }
//
//    @GetMapping("/modify/{id}")
//    public String popupModify(@PathVariable("id") Long id, Model model) {
//        PopupResponse response = popupService.getPopup_admin(id);
//
//        model.addAttribute("popup", response);
//
//        return "homepage/popup/modify";
//    }
}
