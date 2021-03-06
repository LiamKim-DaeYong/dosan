package com.samin.dosan.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @GetMapping("/")
    public String redirect(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("role", getRole());
        return "redirect:/{role}/dashboard";
    }

    private String getRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authority = authentication.getAuthorities()
                .stream().findFirst()
                .get().toString();

        return authority.replaceAll("ROLE_", "").toLowerCase();
    }
}
