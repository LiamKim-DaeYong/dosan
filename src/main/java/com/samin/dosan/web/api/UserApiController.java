package com.samin.dosan.web.api;

import com.samin.dosan.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserApiController {

    private final UserService userService;

}
