package com.company.dto;

import com.company.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class RegistrationDTO {


    @NotNull(message = "mazgi")
     private String email;
    @NotBlank(message = "password is null")
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private ProfileRole role = ProfileRole.ROLE_USER;
    private String mainPhoto;

}