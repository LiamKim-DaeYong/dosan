package com.samin.dosan.web.api.setting;

import com.samin.dosan.domain.setting.course.Course;
import com.samin.dosan.domain.setting.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course/{type}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CourseApiController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity save(@PathVariable String type, @Valid @RequestBody Course saveData) {
        courseService.save(saveData.init(type));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity edit(@Valid @RequestBody Course updateData) {
        courseService.update(updateData.getId(), updateData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        courseService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
