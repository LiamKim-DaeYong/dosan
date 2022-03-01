package com.samin.dosan.web.api.setting;

import com.samin.dosan.domain.setting.course.Course;
import com.samin.dosan.domain.setting.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/setting/course")
public class CourseApiController {

    private final CourseService courseService;

    @PostMapping("/{type}/add")
    public ResponseEntity save(@PathVariable String type, @Valid @RequestBody Course course) {
        course.setCourseType(type);
        courseService.save(course);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{type}/edit")
    public ResponseEntity edit(@Valid @RequestBody Course updateData) {
        courseService.update(updateData);
        return ResponseEntity.ok().build();
    }
}
