package com.samin.dosan.web.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/members")
public class MemberController {

    @GetMapping("/manager")
    public String managers() {
        return "member/manager/managerList";
    }

    @GetMapping("/user")
    public String users() {
        return "member/user/userList";
    }
}
