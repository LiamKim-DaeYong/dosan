package com.samin.dosan.web.api.admin.user.validator;

import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.domain.user.repository.UserRepository;
import com.samin.dosan.web.dto.user.employee.EmployeeSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeUserIdValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeSave.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeSave employeeSave = (EmployeeSave) target;

        Optional<User> findUser = userRepository.findById(employeeSave.getUserId());

        if (!findUser.isEmpty()) {
            errors.rejectValue("userId", "Exist.userId");
        }
    }
}
