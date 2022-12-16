package com.company.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


public class AuthDTO {
    @Email(message = "mazgi email yozmoqchi bold=sang emailga oxshatib yoz")
    private String email;
    @NotNull(message = "password is null")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
