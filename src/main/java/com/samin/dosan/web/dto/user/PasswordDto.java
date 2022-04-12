package com.samin.dosan.web.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class PasswordDto {

    @NotBlank
    private String password;

    @NotBlank
    private String confirm;
}
