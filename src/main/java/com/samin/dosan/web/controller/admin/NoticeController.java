package com.samin.dosan.web.controller.admin;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.domain.notice.Notice;
import com.samin.dosan.domain.notice.NoticeFile;
import com.samin.dosan.domain.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<Notice> result = noticeService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/notice/mainView";
    }

    @GetMapping("/{id}")
    public String detailView(@PathVariable Long id, Model model) {
        Notice notice = noticeService.findById(id);
        model.addAttribute("notice", notice);

        return "admin/notice/detailView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute Notice notice) {
        return "admin/notice/addView";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        Notice notice = noticeService.findById(id);
        model.addAttribute("notice", notice);

        return "admin/notice/editView";
    }

    @GetMapping("/attach/{noticeId}/{fileId}")
    public ResponseEntity download(@PathVariable Long noticeId, @PathVariable Long fileId) throws MalformedURLException {
        Notice notice = noticeService.findById(noticeId);

        NoticeFile noticeFile = notice.getFiles().stream()
                .filter(file -> file.getId().equals(fileId)).findFirst().get();

        String originFilename = noticeFile.getOriginFilename();
        String storedFileName = noticeFile.getStoreFileName();

        return FileUtils.downloadFile(originFilename, storedFileName);
    }
}
