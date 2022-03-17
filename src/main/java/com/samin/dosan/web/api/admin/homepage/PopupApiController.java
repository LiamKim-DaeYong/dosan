package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.popup.PopupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/popup")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PopupApiController {

    private final PopupService popupService;

//    @PostMapping("/save")
//    public RedirectView popupSave(@ModelAttribute PopupRequest request) {
//        Long id = popupService.popupSave_admin(request);
//
//        return new RedirectView("/popup/detail/"+id);
//    }
//
//    @PostMapping("/delete")
//    public boolean popupDelete(@RequestBody List<Long> idList) {
//        boolean result = false;
//
//        if (popupService.popupDelete_admin(idList)) {
//            result = true;
//        }
//
//        return result;
//    }
}
