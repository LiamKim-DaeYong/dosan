package com.samin.dosan.web.controller.homepage;

import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.commonfile.CommonFileService;
import com.samin.dosan.domain.homepage.commonfile.dto.CommonFileResponse;
import com.samin.dosan.domain.homepage.webtoon.WebtoonService;
import com.samin.dosan.domain.homepage.webtoon.dto.WebtoonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * NOTE : 관리자 - 만화퇴계 관리
 * */
@Controller
@RequiredArgsConstructor
@RequestMapping("/webtoon")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class WebtoonController {

    private final WebtoonService webtoonService;
    private final CommonFileService commonFileService;

    @GetMapping
    public String webtoon(@RequestParam(defaultValue = "1") int page, FilterDto filterDto, Model model) {
        Page<WebtoonResponse> webtoonList = webtoonService.getWebtoonList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("webtoonList", webtoonList);

        return "homepage/data/webtoon/list";
    }

    @PostMapping("/page")
    public String webtoonPage(@RequestParam int page, @RequestBody FilterDto filterDto, Model model) {
        Page<WebtoonResponse> webtoonList = webtoonService.getWebtoonList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("webtoonList", webtoonList);

        return "homepage/data/webtoon/list :: webtoonFrag";
    }

    @GetMapping("/regist")
    public String webtoonRegist() {
        return "homepage/data/webtoon/regist";
    }

    @GetMapping("/detail/{id}")
    public String webtoonDetail(@PathVariable("id") Long id, Model model) {
        WebtoonResponse response = webtoonService.getWebtoon_admin(id);
        String pdfName = commonFileService.getFileName(response.getPdfId());
        String previewName = commonFileService.getFileName(response.getPreviewId());

        model.addAttribute("webtoon", response);
        model.addAttribute("pdfName", pdfName);
        model.addAttribute("previewName", previewName);

        return "homepage/data/webtoon/detail";
    }

    @GetMapping("/modify/{id}")
    public String webtoonModify(@PathVariable("id") Long id, Model model) {
        WebtoonResponse response = webtoonService.getWebtoon_admin(id);
        CommonFileResponse pdf = commonFileService.getFile(response.getPdfId());
        CommonFileResponse preview = commonFileService.getFile(response.getPreviewId());

        model.addAttribute("webtoon", response);
        model.addAttribute("pdf", pdf);
        model.addAttribute("preview", preview);

        return "homepage/data/webtoon/modify";
    }
}
