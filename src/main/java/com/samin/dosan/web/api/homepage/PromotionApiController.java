package com.samin.dosan.web.api.homepage;

import com.samin.dosan.domain.homepage.promotion.PromotionService;
import com.samin.dosan.domain.homepage.promotion.dto.PromotionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/promotion")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PromotionApiController {

    private final PromotionService promotionService;

    @PostMapping("/save")
    public RedirectView promotionSave(@ModelAttribute PromotionRequest request) {
        Long id = promotionService.promotionSave_admin(request);

        return new RedirectView("/promotion/detail/"+id);
    }

    @PostMapping("/delete")
    public boolean promotionListDelete(@RequestBody List<Long> idList) {
        boolean result = false;
        if (promotionService.promotionDelete_admin(idList)) {
            result = true;
        }

        return result;
    }

    @GetMapping("/delete/{id}")
    public boolean promotionDelete(@PathVariable("id") Long id) {
        List<Long> idList = new ArrayList<>();
        idList.add(id);

        boolean result = false;
        if (promotionService.promotionDelete_admin(idList)) {
            result = true;
        }

        return result;
    }
}
