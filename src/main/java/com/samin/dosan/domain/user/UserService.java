package com.samin.dosan.domain.user;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public User findById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public Page<User> findAllEmployees(SearchParam searchParam, Long employeesType, Pageable pageable) {
        return userRepository.findAllEmployees(searchParam, employeesType, pageable);
    }

    public Page<User> findAllEducators(SearchParam searchParam, Long employeesType, Pageable pageable) {
        return userRepository.findAllEducators(searchParam, employeesType, pageable);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
