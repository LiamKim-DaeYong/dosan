package com.samin.dosan.web.api;

import com.samin.dosan.domain.user.User;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.domain.user.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserApiController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

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
    public ResponseEntity save(@Valid @RequestBody User user) {
        User initUser = user.newEmployee(UserType.EMPLOYEES, passwordEncoder.encode(user.getPassword()));
        userService.save(initUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/employees/{userId}/detail")
    public ResponseEntity edit(@PathVariable String userId, @Valid @RequestBody User user) {
        userService.edit(userId, user);
        return ResponseEntity.ok().build();
    }
}
