package com.samin.dosan.web.api.homepage;

import com.samin.dosan.domain.homepage.mainImage.MainImageService;
import com.samin.dosan.domain.homepage.mainImage.dto.MainImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mainImage")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class MainImageApiController {

    private final MainImageService mainImageService;

    @PostMapping("/save")
    public RedirectView mainImageSave(@ModelAttribute MainImageRequest request) {
        Long id = mainImageService.mainImageSave_admin(request);

        return new RedirectView("/mainImage/detail/"+id);
    }

    @PostMapping("/delete")
    public boolean mainImageDelete(@RequestBody List<Long> idList) {
        boolean result = false;

        if (mainImageService.mainImageDelete_admin(idList)) {
            result = true;
        }

        return result;
    }
}
