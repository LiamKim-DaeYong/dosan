package com.samin.dosan.web.api.homepage;

import com.samin.dosan.domain.homepage.webtoon.WebtoonService;
import com.samin.dosan.domain.homepage.webtoon.dto.WebtoonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webtoon")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class WebtoonApiController {

    private final WebtoonService webtoonService;

    @PostMapping("/save")
    public RedirectView webtoonSave(@ModelAttribute WebtoonRequest request) {
        Long id = webtoonService.webtoonSave_admin(request);

        return new RedirectView("/webtoon/detail/"+id);
    }

    @PostMapping("/delete")
    public boolean webtoonListDelete(@RequestBody List<Long> idList) {
        boolean result = false;
        if (webtoonService.webtoonDelete_admin(idList)) {
            result = true;
        }

        return result;
    }

    @GetMapping("/delete/{id}")
    public boolean webtoonDelete(@PathVariable("id") Long id) {
        List<Long> idList = new ArrayList<>();
        idList.add(id);

        boolean result = false;
        if (webtoonService.webtoonDelete_admin(idList)) {
            result = true;
        }

        return result;
    }
}
