package com.samin.dosan.web.controller.admin.user;

import com.samin.dosan.core.code.Gender;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.educator_code.EducatorCode;
import com.samin.dosan.domain.setting.educator_code.EducatorCodeService;
import com.samin.dosan.domain.setting.educator_code.EducatorCodeType;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCodeService;
import com.samin.dosan.domain.user.educator.EducatorService;
import com.samin.dosan.domain.user.educator.entity.Educator;
import com.samin.dosan.web.dto.user.educator.EducatorSave;
import com.samin.dosan.web.dto.user.educator.EducatorUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/user/educators")
public class EducatorController {

    private final EducatorService educatorService;
    private final EducatorCodeService educatorCodeService;
    private final FormerJobCodeService formerJobCodeService;

    @GetMapping
    public String mainView(@RequestParam(name = "educatorType", required = false) Long educatorCodeId,
                           @ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {

        Page<Educator> result = educatorService.findAll(searchParam, educatorCodeId, pageable);
        model.addAttribute("result", result);

        return "admin/user/educators/mainView";
    }

    @GetMapping("/{userId}")
    public String detailView(@PathVariable String userId, Model model) {
        Educator educator = educatorService.findByUserId(userId);
        model.addAttribute("educator", educator);

        return "admin/user/educators/detailView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute("educator") EducatorSave educatorSave) {
        return "admin/user/educators/addView";
    }

    @GetMapping("/{userId}/edit")
    public String editView(@PathVariable String userId, Model model) {
        Educator educator = educatorService.findByUserId(userId);
        EducatorUpdate educatorUpdate = EducatorUpdate.fromEntity(educator);

        model.addAttribute("educator", educatorUpdate);

        return "admin/user/educators/editView";
    }

    /*================== 공통코드 ==================*/
    @ModelAttribute("genders")
    public Gender[] genders() { return Gender.values(); }

    @ModelAttribute("formerJobCodes")
    public List<FormerJobCode> formerJobCodes() { return formerJobCodeService.findAllList(); }

    @ModelAttribute("educatorTypes")
    public List<EducatorCode> educatorTypes() { return educatorCodeService.findAll(EducatorCodeType.TYPE); }

    @ModelAttribute("educatorAssignedTasks")
    public List<EducatorCode> educatorAssignedTasks() { return educatorCodeService.findAll(EducatorCodeType.ASSIGNED_TASK); }

    @ModelAttribute("educatorTeams")
    public List<EducatorCode> educatorTeams() { return educatorCodeService.findAll(EducatorCodeType.TEAM); }

    @ModelAttribute("educatorBranches")
    public List<EducatorCode> educatorBranches() { return educatorCodeService.findAll(EducatorCodeType.BRANCH); }
}
