package com.samin.dosan.web.api.admin.user;

import com.samin.dosan.domain.user.Role;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.domain.user.educator.EducatorService;
import com.samin.dosan.domain.user.educator.entity.Educator;
import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.web.api.admin.user.validator.EducatorUserIdValidator;
import com.samin.dosan.web.dto.user.educator.EducatorSave;
import com.samin.dosan.web.dto.user.educator.EducatorUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user/educators")
public class EducatorApiController {

    private final UserService userService;
    private final EducatorService educatorService;
    private final EducatorUserIdValidator educatorUserIdValidator;

    @InitBinder("educatorSave")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(educatorUserIdValidator);
    }

    @PostMapping("/add")
    public ResponseEntity save(@Valid @RequestBody EducatorSave saveData) {
        User user = User.of(saveData.getUserId(), saveData.getPassword(), saveData.getUserNm(), Role.ROLE_EDUCATOR);
        userService.save(user);

        Educator educator = Educator.of(saveData, user);
        educatorService.save(educator);

        return ResponseEntity.ok(user.getUserId());
    }

    @PutMapping("/{userId}/edit")
    public ResponseEntity update(@PathVariable String userId, @Valid @RequestBody EducatorUpdate updateData) {
        userService.update(userId, updateData.getUserNm());
        educatorService.update(userId, updateData);
        return ResponseEntity.ok(userId);
    }
}
