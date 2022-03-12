package com.samin.dosan.web.controller.setting;

import com.samin.dosan.domain.signature.Signature;
import com.samin.dosan.domain.signature.SignatureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/setting/signature")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SignatureController {

    private final SignatureService signatureService;

    @GetMapping
    public String signature(Model model) {
        Signature signature = signatureService.findTypeOfSystem();
        model.addAttribute("signature", signature);

        return "setting/signature";
    }

    @PostMapping
    public String save(@RequestParam MultipartFile file) throws IOException {
        signatureService.save(file);
        return "redirect:/setting/signature";
    }

    @ResponseBody
    @GetMapping("/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + signatureService.getFullPath(filename));
    }
}
