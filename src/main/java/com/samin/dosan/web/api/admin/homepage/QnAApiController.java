package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.qna.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/qna")
public class QnAApiController {

    private final QnaService qnaService;

    @PostMapping("/{id}/comment")
    public ResponseEntity comment(@PathVariable Long id, @RequestBody Map<String, String> comment) {
        qnaService.comment(id, comment.get("comment"));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/comment/delete")
    public ResponseEntity commentDelete(@PathVariable Long id) {
        qnaService.commentDelete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        qnaService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
