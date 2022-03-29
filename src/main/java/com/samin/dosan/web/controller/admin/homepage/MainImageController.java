package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.domain.homepage.main_image.MainImage;
import com.samin.dosan.domain.homepage.main_image.MainImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/main-image")
public class MainImageController {

    private final MainImageService mainImageService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<MainImage> result = mainImageService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/homepage/main_image/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute MainImage mainImage) {
        return "admin/homepage/main_image/addView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("mainImage", mainImageService.findById(id));

        return "admin/homepage/main_image/detailView";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        model.addAttribute("mainImage", mainImageService.findById(id));

        return "admin/homepage/main_image/editView";
    }

    @GetMapping("/attach/{id}")
    public ResponseEntity download(@PathVariable Long id) throws MalformedURLException {
        MainImage mainImage = mainImageService.findById(id);

        String originFilename = mainImage.getOriginFileName();
        String storedFilename = mainImage.getStoreFileName();

        return FileUtils.downloadFile(originFilename, storedFilename);
    }
}
