package com.samin.dosan.web.notice;

import com.samin.dosan.domain.notice.NoticeService;
import com.samin.dosan.web.notice.dto.NoticeDto;
import com.samin.dosan.web.param.SearchParam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String list(SearchParam searchParam, Model model) {
        List<NoticeDto> noticeList = noticeService.findAll(searchParam)
                .stream().map(notice -> new NoticeDto(notice))
                .collect(Collectors.toList());

        model.addAttribute("noticeList", noticeList);

        return "notice/noticeList";
    }

    @GetMapping("/admin/add")
    public String addForm() {
        return "notice/noticeAddForm";
    }

    @PostMapping("/admin/add")
    public void save() {

    }
}
