package com.samin.dosan.web.controller.homepage;

import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.commonfile.CommonFileService;
import com.samin.dosan.domain.homepage.commonfile.dto.CommonFileResponse;
import com.samin.dosan.domain.homepage.inquiry.InquiryService;
import com.samin.dosan.domain.homepage.inquiry.dto.InquiryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * NOTE : 관리자 - 문의하기 관리
 * */
@Controller
@RequiredArgsConstructor
@RequestMapping("/inquiry")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class InquiryController {

    private final InquiryService inquiryService;
    private final CommonFileService commonFileService;

    @GetMapping
    public String inquiry(@RequestParam(defaultValue = "1") int page, FilterDto filterDto, Model model) {
        Page<InquiryResponse> inquiryList = inquiryService.getInquiryList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("inquiryList", inquiryList);

        return "homepage/inquiry/list";
    }

    @PostMapping("/page")
    public String inquiryPage(@RequestParam int page, FilterDto filterDto, Model model) {
        Page<InquiryResponse> inquiryList = inquiryService.getInquiryList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("inquiryList", inquiryList);

        return "homepage/inquiry/list :: inquiryFrag";
    }

    @GetMapping("/detail/{id}")
    public String inquiryDetail(@PathVariable("id") Long id, Model model) {
        List<Long> fileIdList = new ArrayList<>();
        InquiryResponse inquiry = inquiryService.getInquiry_admin(id);

        if (inquiry.getInquiryFileList().size() > 0) {
            for (InquiryResponse.InquiryFileResponse inquiryFile : inquiry.getInquiryFileList()) {
                fileIdList.add(inquiryFile.getFileId());
            }
        }

        List<CommonFileResponse> commonFileList = commonFileService.getFiles(fileIdList);

        model.addAttribute("inquiry", inquiry);
        model.addAttribute("fileList", commonFileList);

        return "homepage/inquiry/detail";
    }
}
