package com.samin.dosan.web.api.educator.training_archive;

import com.samin.dosan.domain.training_archive.lesson.Lesson;
import com.samin.dosan.domain.training_archive.lesson.LessonService;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.web.dto.training_archive.LessonSave;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/educator/training-archive/lesson/{type}")
public class LessonApiController {

    private final LessonService lessonService;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity save(@Valid @RequestBody LessonSave saveData) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        saveData.setUser(userService.findById(username));

        Long id = lessonService.save(Lesson.of(saveData));

        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody LessonSave updateData) {
        lessonService.update(id, updateData);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        lessonService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
