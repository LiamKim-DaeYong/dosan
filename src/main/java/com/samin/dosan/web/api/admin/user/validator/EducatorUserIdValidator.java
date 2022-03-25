package com.samin.dosan.web.api.admin.user.validator;

import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.domain.user.repository.UserRepository;
import com.samin.dosan.web.dto.user.educator.EducatorSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EducatorUserIdValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return EducatorSave.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EducatorSave educatorSave = (EducatorSave) target;

        Optional<User> findUser = userRepository.findById(educatorSave.getUserId());

        if (!findUser.isEmpty()) {
            errors.rejectValue("userId", "Exist.userId");
        }
    }
}
