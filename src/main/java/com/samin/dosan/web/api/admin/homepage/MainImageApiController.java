package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.main_image.MainImageService;
import com.samin.dosan.web.dto.homepage.mainImage.MainImageSave;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mainImage")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class MainImageApiController {

    private final MainImageService mainImageService;

    @PostMapping("/add")
    public ResponseEntity mainImageSave(@Valid @ModelAttribute MainImageSave saveData) {
        return ResponseEntity.ok().build();
    }
//
//    @PostMapping("/delete")
//    public boolean mainImageDelete(@RequestBody List<Long> idList) {
//        boolean result = false;
//
//        if (mainImageService.mainImageDelete_admin(idList)) {
//            result = true;
//        }
//
//        return result;
//    }
}
