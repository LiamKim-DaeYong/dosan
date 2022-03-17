package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.commonfile.CommonFileService;
import com.samin.dosan.domain.homepage.impression.Impression;
import com.samin.dosan.domain.homepage.impression.ImpressionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/impression")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ImpressionController {

    private final ImpressionService impressionService;
    private final CommonFileService commonFileService;

    @GetMapping
    public String impression(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<Impression> result = impressionService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "homepage/impression/list";
    }

    @PostMapping("/page")
    public String impressionPage(@RequestParam int page, @RequestBody FilterDto filterDto, Model model) {
//        Page<ImpressionResponse> impressionList = impressionService.getImpressionList_admin(page, filterDto);
//
//        model.addAttribute("page", page);
//        model.addAttribute("filter", filterDto);
//        model.addAttribute("impressionList", impressionList);

        return "homepage/impression/list :: impressionFrag";
    }

    @GetMapping("/detail/{id}")
    public String impressionDetail(@PathVariable("id") Long id, Model model) {
//        List<Long> fileIdList = new ArrayList<>();
//        ImpressionResponse impression = impressionService.getImpression_admin(id);
//
//        if (impression.getImpressionFileList().size() > 0) {
//            for (ImpressionResponse.ImpressionFileResponse impressionFile : impression.getImpressionFileList()) {
//                fileIdList.add(impressionFile.getFileId());
//            }
//        }
//
//        List<CommonFileResponse> commonFileList = commonFileService.getFiles(fileIdList);
//
//        model.addAttribute("impression", impression);
//        model.addAttribute("fileList", commonFileList);

        return "homepage/impression/detail";
    }
}
