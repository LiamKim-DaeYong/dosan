package com.samin.dosan.web.api.homepage;

import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.advice.AdviceService;
import com.samin.dosan.domain.homepage.advice.dto.AdviceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/advice")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdviceApiController {

    private final AdviceService adviceService;

    @PostMapping("/page")
    public Page<AdviceResponse> advicePage(@RequestParam int page, @RequestBody FilterDto filterDto) {
        Page<AdviceResponse> responseList = adviceService.gets(page, filterDto);

        return responseList;
    }

    @PostMapping("/check")
    public boolean adviceCheck(@RequestBody Long id) {
        boolean result = false;
        if (adviceService.adviceCheck(id)) {
            result = true;
        }

        return result;
    }

    @PostMapping("/delete")
    public boolean adviceDelete(@RequestBody List<Long> idList) {
        boolean result = false;
        if (adviceService.adviceDelete(idList)) {
            result = true;
        }

        return result;
    }
}
