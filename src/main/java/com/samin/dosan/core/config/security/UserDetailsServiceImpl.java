package com.samin.dosan.core.config.security;

import com.samin.dosan.domain.user.SessionUser;
import com.samin.dosan.domain.user.User;
import com.samin.dosan.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public SessionUser loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userService.findById(userId);

        if (user == null) {
            throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
        }

        SessionUser sessionUser = SessionUser.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .userNm(user.getUserNm())
                .role(user.getRole())
                .enabled(true)
                .build();

        return sessionUser;
    }
}
