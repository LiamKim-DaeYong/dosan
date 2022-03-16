package com.samin.dosan.web.api.homepage;

import com.samin.dosan.domain.homepage.inquiry.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inquiry")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class InquiryApiController {

    private final InquiryService inquiryService;

    @PostMapping("/delete")
    public boolean inquiryListDelete(@RequestBody List<Long> idList) {
        boolean result = false;
        if (inquiryService.inquiryDelete_admin(idList)) {
            result = true;
        }

        return result;
    }

    @GetMapping("/delete/{id}")
    public boolean inquiryDelete(@PathVariable("id") Long id) {
        List<Long> idList = new ArrayList<>();
        idList.add(id);

        boolean result = false;
        if (inquiryService.inquiryDelete_admin(idList)) {
            result = true;
        }

        return result;
    }

    @PostMapping("/comment/save")
    public Long inquiryCommentSave(@RequestBody Map<String, String> commentVo) {
        Long inquiryId = inquiryService.inquiryCommentSave_admin(commentVo);

        return inquiryId;
    }

    @GetMapping("/comment/delete/{id}")
    public RedirectView inquiryCommentDelete(@PathVariable("id") Long id) {
        Long inquiryId = inquiryService.inquiryCommentDelete_admin(id);

        return new RedirectView("/inquiry/detail/"+inquiryId);
    }
}
