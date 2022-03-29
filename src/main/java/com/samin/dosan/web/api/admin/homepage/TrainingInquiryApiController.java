package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.training_inquiry.TrainingInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/training-inquiry")
public class TrainingInquiryApiController {

    private final TrainingInquiryService trainingInquiryService;

    @PutMapping("/check")
    public ResponseEntity check(@RequestBody Long id) {
        trainingInquiryService.check(id);
        return ResponseEntity.ok().build();
    }
}
