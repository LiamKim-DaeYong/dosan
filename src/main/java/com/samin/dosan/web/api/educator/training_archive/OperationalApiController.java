package com.samin.dosan.web.api.educator.training_archive;

import com.samin.dosan.domain.training_archive.operational.OperationalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/educator/training_archive/operational")
public class OperationalApiController {

    private final OperationalService operationalService;

}
