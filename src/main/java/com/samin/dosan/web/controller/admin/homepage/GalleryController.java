package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.commonfile.CommonFileService;
import com.samin.dosan.domain.homepage.commonfile.dto.CommonFileResponse;
import com.samin.dosan.domain.homepage.gallery.GalleryService;
import com.samin.dosan.domain.homepage.gallery.dto.GalleryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * NOTE : 관리자 - 포토갤러리 관리
 * */
@Controller
@RequiredArgsConstructor
@RequestMapping("/gallery")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class GalleryController {

    private final GalleryService galleryService;
    private final CommonFileService commonFileService;

    @GetMapping
    public String gallery(@RequestParam(defaultValue = "1") int page, FilterDto filterDto, Model model) {
        Page<GalleryResponse> galleryList = galleryService.getGalleryList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("galleryList", galleryList);

        return "homepage/board/gallery/list";
    }

    @PostMapping("/page")
    public String galleryPage(@RequestParam int page, @RequestBody FilterDto filterDto, Model model) {
        Page<GalleryResponse> galleryList = galleryService.getGalleryList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("galleryList", galleryList);

        return "homepage/board/gallery/list :: galleryFrag";
    }

    @GetMapping("/regist")
    public String galleryRegist() {
        return "homepage/board/gallery/regist";
    }

    @GetMapping("/detail/{id}")
    public String galleryDetail(@PathVariable("id") Long id, Model model) {
        List<Long> fileIdList = new ArrayList<>();
        GalleryResponse gallery = galleryService.getGallery_admin(id);

        if (gallery.getChildList().size() > 0) {
            for (GalleryResponse.GalleryFileResponse galleryFile : gallery.getChildList()) {
                fileIdList.add(galleryFile.getFileId());
            }
        }

        List<CommonFileResponse> commonFileList = commonFileService.getFiles(fileIdList);

        model.addAttribute("gallery", gallery);
        model.addAttribute("fileList", commonFileList);

        return "homepage/board/gallery/detail";
    }

    @GetMapping("/modify/{id}")
    public String galleryModify(@PathVariable("id") Long id, Model model) {
        List<Long> fileIdList = new ArrayList<>();
        GalleryResponse gallery = galleryService.getGallery_admin(id);

        if (gallery.getChildList().size() > 0) {
            for (GalleryResponse.GalleryFileResponse galleryFile : gallery.getChildList()) {
                fileIdList.add(galleryFile.getFileId());
            }
        }

        model.addAttribute("gallery", gallery);
        model.addAttribute("childList", fileIdList);

        return "homepage/board/gallery/modify";
    }
}
