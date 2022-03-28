package com.samin.dosan.web.api.educator.training_archive;

import com.samin.dosan.domain.training_archive.branch.Branch;
import com.samin.dosan.domain.training_archive.branch.BranchService;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.web.dto.training_archive.BranchSave;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/educator/training-archive/branch/{type}")
public class BranchApiController {

    private final BranchService branchService;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity save(@Valid @RequestBody BranchSave saveData) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        saveData.setUser(userService.findById(username));

        Long id = branchService.save(Branch.of(saveData));

        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody BranchSave updateData) {
        branchService.update(id, updateData);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        branchService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
