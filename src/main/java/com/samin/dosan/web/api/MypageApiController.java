package com.samin.dosan.web.api;

import com.samin.dosan.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class MypageApiController {

    private final UserService userService;

    @PostMapping("/admin")
    public ResponseEntity save(@RequestBody String password) {
        userService.updatePassword("admin", password);
        return ResponseEntity.ok().build();
    }
}
