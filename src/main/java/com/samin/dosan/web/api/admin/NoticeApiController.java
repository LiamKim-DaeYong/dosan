package com.samin.dosan.web.api.admin;

import com.samin.dosan.domain.notice.NoticeService;
import com.samin.dosan.web.dto.notice.NoticeSave;
import com.samin.dosan.web.dto.notice.NoticeUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/notice")
public class NoticeApiController {

    private final NoticeService noticeService;

    @PostMapping("/add")
    public ResponseEntity save(@Valid NoticeSave saveData) {
        Long id = noticeService.save(saveData);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity update(@PathVariable Long id, @Valid NoticeUpdate updateData) {
        noticeService.update(id, updateData);
        return ResponseEntity.ok(id);
    }
}
