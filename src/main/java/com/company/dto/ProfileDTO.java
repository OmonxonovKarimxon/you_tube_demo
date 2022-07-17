package com.company.dto;

import com.company.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {


    private Integer id;

    private  String  name;
    private String surname;
    private String email;
    private ProfileRole role;
    private String password;
    private String jwt;
    private AttachDTO mainPhoto;
}
