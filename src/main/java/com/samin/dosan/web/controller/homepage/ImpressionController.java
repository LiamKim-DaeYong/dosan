package com.samin.dosan.web.controller.homepage;

import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.commonfile.CommonFileService;
import com.samin.dosan.domain.homepage.commonfile.dto.CommonFileResponse;
import com.samin.dosan.domain.homepage.impression.ImpressionService;
import com.samin.dosan.domain.homepage.impression.dto.ImpressionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * NOTE : 관리자 - 수련소감 관리
 * */
@Controller
@RequiredArgsConstructor
@RequestMapping("/impression")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ImpressionController {

    private final ImpressionService impressionService;
    private final CommonFileService commonFileService;

    @GetMapping
    public String impression(@RequestParam(defaultValue = "1") int page, FilterDto filterDto, Model model) {
        Page<ImpressionResponse> impressionList = impressionService.getImpressionList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("impressionList", impressionList);

        return "homepage/impression/list";
    }

    @PostMapping("/page")
    public String impressionPage(@RequestParam int page, @RequestBody FilterDto filterDto, Model model) {
        Page<ImpressionResponse> impressionList = impressionService.getImpressionList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("impressionList", impressionList);

        return "homepage/impression/list :: impressionFrag";
    }

    @GetMapping("/detail/{id}")
    public String impressionDetail(@PathVariable("id") Long id, Model model) {
        List<Long> fileIdList = new ArrayList<>();
        ImpressionResponse impression = impressionService.getImpression_admin(id);

        if (impression.getImpressionFileList().size() > 0) {
            for (ImpressionResponse.ImpressionFileResponse impressionFile : impression.getImpressionFileList()) {
                fileIdList.add(impressionFile.getFileId());
            }
        }

        List<CommonFileResponse> commonFileList = commonFileService.getFiles(fileIdList);

        model.addAttribute("impression", impression);
        model.addAttribute("fileList", commonFileList);

        return "homepage/impression/detail";
    }
}
