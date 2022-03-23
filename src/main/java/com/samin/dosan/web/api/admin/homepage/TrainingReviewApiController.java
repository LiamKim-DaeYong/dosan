package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.training_review.TrainingReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/training-review")
public class TrainingReviewApiController {

    private final TrainingReviewService trainingReviewService;

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        trainingReviewService.delete(ids);

        return ResponseEntity.ok().build();
    }
}
