package com.company.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthDTO {
    @Email(message = "mazgi email yozmoqchi bold=sang emailga oxshatib yoz")
    private String email;
    @NotNull(message = "password is null")
    private String password;
}
