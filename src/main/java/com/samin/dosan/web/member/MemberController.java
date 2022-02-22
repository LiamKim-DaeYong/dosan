package com.samin.dosan.web.member;

import com.samin.dosan.domain.member.MemberService;
import com.samin.dosan.web.member.dto.user.UserDto;
import com.samin.dosan.web.member.dto.user.UserSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/manager")
    public String managersPage(Model model) {
        List<UserDto> managers = memberService.findAllUser();
        model.addAttribute("managers", managers);
        log.info("managers={}", managers);
        return "member/manager/managers";
    }

    @GetMapping("/user")
    public String usersPage() {
        return "member/user/users";
    }

    @GetMapping("/user/add")
    public String addUser(Model model) {
        model.addAttribute("user", new UserSaveForm());
        return "member/user/addForm";
    }

    @PostMapping("/user/add")
    public String saveUser(@Valid @ModelAttribute("user") UserSaveForm userSaveForm,
                           BindingResult bindingResult, Model model,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "member/user/addForm";
        }

        String initPassword = memberService.save(userSaveForm);
        if (initPassword != null) {
            model.addAttribute("initPassword", initPassword);
        }

        redirectAttributes.addAttribute("userId", userSaveForm.getUserId());
        return "redirect:/members/user/{userId}";
    }
}
