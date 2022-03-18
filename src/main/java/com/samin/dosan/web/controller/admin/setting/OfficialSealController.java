package com.samin.dosan.web.controller.admin.setting;

import com.samin.dosan.domain.setting.official_seal.OfficialSeal;
import com.samin.dosan.domain.setting.official_seal.SignatureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/setting/official-seal")
public class OfficialSealController {

    private final SignatureService signatureService;

    @GetMapping
    public String mainView(Model model) {
        OfficialSeal officialSeal = signatureService.findTypeOfSystem();
        model.addAttribute("signature", officialSeal);

        return "admin/setting/official_seal/mainView";
    }

    @PostMapping
    public String save(@RequestParam MultipartFile file) throws IOException {
        signatureService.save(file);
        return "redirect:/admin/setting/signature";
    }

    @ResponseBody
    @GetMapping("/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + signatureService.getFullPath(filename));
    }
}
