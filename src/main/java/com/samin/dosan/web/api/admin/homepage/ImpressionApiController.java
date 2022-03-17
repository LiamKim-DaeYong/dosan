package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.impression.ImpressionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/impression")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ImpressionApiController {

    private final ImpressionService impressionService;

    @PostMapping("/delete")
    public boolean impressionListDelete(@RequestBody List<Long> idList) {
//        boolean result = false;
//        if (impressionService.impressionDelete_admin(idList)) {
//            result = true;
//        }

//        return result;
        return true;
    }

    @GetMapping("/delete/{id}")
    public boolean impressionDelete(@PathVariable("id") Long id) {
//        List<Long> idList= new ArrayList<>();
//        idList.add(id);
//
//        boolean result = false;
//        if (impressionService.impressionDelete_admin(idList)) {
//            result = true;
//        }

        return true;
//        return result;
    }
}
