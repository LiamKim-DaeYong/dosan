package com.samin.dosan.web.controller.homepage;

import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.popup.PopupService;
import com.samin.dosan.domain.homepage.popup.dto.PopupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String popup(@RequestParam(defaultValue = "1") int page, FilterDto filterDto, Model model) {
        Page<PopupResponse> popupList = popupService.getPopupList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("popupList", popupList);

        return "homepage/popup/list";
    }

    @PostMapping("/page")
    public String popupPage(@RequestParam int page, @RequestBody FilterDto filterDto, Model model) {
        Page<PopupResponse> popupList = popupService.getPopupList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("popupList", popupList);

        return "homepage/popup/list :: popupFrag";
    }

    @GetMapping("/regist")
    public String popupRegist() {
        return "homepage/popup/regist";
    }

    @GetMapping("/detail/{id}")
    public String popupDetail(@PathVariable("id") Long id, Model model) {
        PopupResponse response = popupService.getPopup_admin(id);

        model.addAttribute("popup", response);

        return "homepage/popup/detail";
    }

    @GetMapping("/modify/{id}")
    public String popupModify(@PathVariable("id") Long id, Model model) {
        PopupResponse response = popupService.getPopup_admin(id);

        model.addAttribute("popup", response);

        return "homepage/popup/modify";
    }
}
