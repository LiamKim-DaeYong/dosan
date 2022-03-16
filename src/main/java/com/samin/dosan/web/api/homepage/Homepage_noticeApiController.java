package com.samin.dosan.web.api.homepage;

import com.samin.dosan.domain.homepage.notice.Homepage_noticeService;
import com.samin.dosan.domain.homepage.notice.dto.NoticeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/homepage_notice")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class Homepage_noticeApiController {

    private final Homepage_noticeService noticeService;

    @PostMapping("/save")
    public RedirectView noticeSave(@ModelAttribute NoticeRequest request) {
        Long id = noticeService.noticeSave_admin(request);

        return new RedirectView("/homepage_notice/detail/"+id);
    }

    @PostMapping("/delete")
    public boolean noticeListDelete(@RequestBody List<Long> idList) {
        boolean result = false;
        if (noticeService.noticeDelete_admin(idList)) {
            result = true;
        }

        return result;
    }

    @GetMapping("/delete/{id}")
    public boolean noticeDelete(@PathVariable("id") Long id) {
        List<Long> idList = new ArrayList<>();
        idList.add(id);

        boolean result = false;
        if (noticeService.noticeDelete_admin(idList)) {
            result = true;
        }

        return result;
    }
}
