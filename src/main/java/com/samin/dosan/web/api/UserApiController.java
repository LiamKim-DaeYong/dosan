package com.samin.dosan.web.api;

import com.samin.dosan.domain.user.User;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.web.dto.user.EmployeeSave;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.samin.dosan.domain.user.User.newEmployee;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/check/id")
    public ResponseEntity checkId(@RequestBody Map<String, String> userIdMap) {
        return ResponseEntity.ok(userService.existById(userIdMap.get("userId")));
    }

    @PostMapping("/{userId}/password/init")
    public ResponseEntity initPassword(@PathVariable String userId) {
        userService.initPassword(userId);
        return ResponseEntity.ok().build();
    }

    /* ========== 임직원 ========== */
    @PostMapping("/employees/add")
    public ResponseEntity save(@Valid @RequestBody EmployeeSave saveData) {
        userService.save(newEmployee(saveData));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/employees/{userId}/detail")
    public ResponseEntity edit(@PathVariable String userId, @Valid @RequestBody User user) {
        userService.edit(userId, user);
        return ResponseEntity.ok().build();
    }
}
