package com.samin.dosan.web.controller.homepage;

import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.commonfile.CommonFileService;
import com.samin.dosan.domain.homepage.commonfile.dto.CommonFileResponse;
import com.samin.dosan.domain.homepage.notice.Homepage_noticeService;
import com.samin.dosan.domain.homepage.notice.dto.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * NOTE : 관리자 - 공지사항 관리
 * */
@Controller
@RequiredArgsConstructor
@RequestMapping("/homepage_notice")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class Homepage_noticeController {

    private final Homepage_noticeService noticeService;
    private final CommonFileService commonFileService;

    @GetMapping
    public String notice(@RequestParam(defaultValue = "1") int page, FilterDto filterDto, Model model) {
        Page<NoticeResponse> noticeList = noticeService.getNoticeList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("noticeList", noticeList);

        return "homepage/board/notice/list";
    }

    @PostMapping("/page")
    public String noticePage(@RequestParam int page, @RequestBody FilterDto filterDto, Model model) {
        Page<NoticeResponse> noticeList = noticeService.getNoticeList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("noticeList", noticeList);

        return "homepage/board/notice/list :: noticeFrag";
    }

    @GetMapping("/regist")
    public String noticeRegist() {
        return "homepage/board/notice/regist";
    }

    @GetMapping("/detail/{id}")
    public String noticeDetail(@PathVariable("id") Long id, Model model) {
        List<Long> fileIdList = new ArrayList<>();
        NoticeResponse notice = noticeService.getNotice_admin(id);

        if (notice.getChildList().size() > 0) {
            for (NoticeResponse.NoticeFileResponse noticeFile : notice.getChildList()) {
                fileIdList.add(noticeFile.getFileId());
            }
        }

        List<CommonFileResponse> commonFileList = commonFileService.getFiles(fileIdList);

        model.addAttribute("notice", notice);
        model.addAttribute("fileList", commonFileList);

        return "homepage/board/notice/detail";
    }

    @GetMapping("/modify/{id}")
    public String noticeModify(@PathVariable("id") Long id, Model model) {
        List<Long> fileIdList = new ArrayList<>();
        List<Long> imgIdList = new ArrayList<>();
        NoticeResponse notice = noticeService.getNotice_admin(id);

        if (notice.getChildList() != null) {
            for (NoticeResponse.NoticeFileResponse noticeFile : notice.getChildList()) {
                if (noticeFile.getIsFile().equals("Y")) {
                    fileIdList.add(noticeFile.getFileId());
                }

                if (noticeFile.getIsFile().equals("N")) {
                    imgIdList.add(noticeFile.getFileId());
                }
            }
        }

        List<CommonFileResponse> filList = commonFileService.getFiles(fileIdList);
        List<CommonFileResponse> imgList = commonFileService.getFiles(imgIdList);

        model.addAttribute("notice", notice);
        model.addAttribute("fileList", filList);
        model.addAttribute("imgList", imgList);

        return "/homepage/board/notice/modify";
    }
}
