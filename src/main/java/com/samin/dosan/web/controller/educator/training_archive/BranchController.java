package com.samin.dosan.web.controller.educator.training_archive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("BranchArchive")
@RequiredArgsConstructor
@RequestMapping("/educator/training_archive/branch")
// 지부자료실
public class BranchController {

//    private final BranchArchiveService branchArchiveService;

    @GetMapping
    public String mainView() {
        return "/educator/training_archive/branch/mainView";
    }

    @GetMapping("/detail")
    public String detailView() {
        return "/educator/training_archive/branch/detailView";
    }

    @GetMapping("/add")
    public String addView() {
        return "/educator/training_archive/branch/addView";
    }

    @GetMapping("/edit")
    public String editView() {
        return "/educator/training_archive/branch/editView";
    }
}
