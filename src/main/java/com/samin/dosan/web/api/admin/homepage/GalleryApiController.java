package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.gallery.GalleryService;
import com.samin.dosan.domain.homepage.gallery.dto.GalleryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gallery")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class GalleryApiController {

    private final GalleryService galleryService;

    @PostMapping("/save")
    public RedirectView gallerySave(@ModelAttribute GalleryRequest request) {
        Long id = galleryService.gallerySave_admin(request);

        return new RedirectView("/gallery/detail/"+id);
    }

    @PostMapping("/delete")
    public boolean galleryListDelete(@RequestBody List<Long> idList) {
        boolean result = false;
        if (galleryService.galleryDelete_admin(idList)) {
            result = true;
        }

        return result;
    }

    @GetMapping("/delete/{id}")
    public boolean galleryDelete(@PathVariable("id") Long id) {
        List<Long> idList = new ArrayList<>();
        idList.add(id);

        boolean result = false;
        if (galleryService.galleryDelete_admin(idList)) {
            result = true;
        }

        return result;
    }
}
