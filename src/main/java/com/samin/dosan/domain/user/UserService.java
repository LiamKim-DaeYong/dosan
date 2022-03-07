package com.samin.dosan.domain.user;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private Page<User> findAllManager(SearchParam searchParam, ManagerType managerType, Pageable pageable) {
        return userRepository.findAllManager(searchParam, managerType, pageable);
    }

    public User findById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
