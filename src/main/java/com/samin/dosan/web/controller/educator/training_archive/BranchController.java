package com.samin.dosan.web.controller.educator.training_archive;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.training_archive.branch.BranchType;
import com.samin.dosan.domain.training_archive.branch.Branch;
import com.samin.dosan.domain.training_archive.branch.BranchService;
import com.samin.dosan.domain.training_archive.branch.NoticeYnType;
import com.samin.dosan.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
@RequestMapping("/educator/training-archive/branch/{type}")
public class BranchController {

    private final BranchService branchService;
    private final UserService userService;

    @ModelAttribute("branchTypes")
    public BranchType[] branchTypes() {
        return BranchType.values();
    }

    @ModelAttribute("branchType")
    public BranchType branchType(@PathVariable String type) {
        return BranchType.valueOf(StrUtils.urlToEnumName(type));
    }

    @GetMapping
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<Branch> result = branchService.findAll(searchParam, pageable, type);
        model.addAttribute("result", result);

        return "/educator/training_archive/branch/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute Branch branch, Model model) {
        model.addAttribute("noticeYnTypes", NoticeYnType.values());

        return "/educator/training_archive/branch/addView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("branch", branchService.findById(id));
        return "/educator/training_archive/branch/detailView";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        model.addAttribute("noticeYnTypes", NoticeYnType.values());
        model.addAttribute("branch", branchService.findById(id));

        return "/educator/training_archive/branch/editView";
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i < 1001; i++) {
            Branch branch = Branch.builder()
                    .user(userService.findById("educator"))
                    .branchType(BranchType.DAEGU)
                    .title("제목"+i)
                    .content("내용"+i)
                    .noticeYnType(NoticeYnType.Y)
                    .used(Used.Y)
                    .build();

            branchService.save(branch);
        }
    }
}
