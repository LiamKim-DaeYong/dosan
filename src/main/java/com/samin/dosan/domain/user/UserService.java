package com.samin.dosan.domain.user;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<User> findAllEmployees(SearchParam searchParam, Long employeesType, Pageable pageable) {
        return userRepository.findAllEmployees(searchParam, employeesType, pageable);
    }

    public Page<User> findAllEducators(SearchParam searchParam, Long educatorsType, Pageable pageable) {
        return userRepository.findAllEducators(searchParam, educatorsType, pageable);
    }

    public User findById(String userId) {
        return userRepository.findById(userId).get();
    }

    public boolean existById(String userId) {
        return userRepository.existById(userId);
    }

    @Transactional
    public User save(User user) {
        user.updatePassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public void edit(String userId, User user) {
        User findUser = findById(userId);
        User changeUser = user.edit(findUser);
        userRepository.save(changeUser);
    }

    @Transactional
    public void initPassword(String userId) {
        User user = findById(userId);
        user.updatePassword(passwordEncoder.encode("0000"));
    }

    @Transactional
    public void updatePassword(String userId, String newPassword) {
        User user = findById(userId);
        user.updatePassword(passwordEncoder.encode(newPassword));
    }
}
