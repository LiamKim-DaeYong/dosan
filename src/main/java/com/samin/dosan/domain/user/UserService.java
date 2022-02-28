package com.samin.dosan.domain.user;

import com.samin.dosan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
