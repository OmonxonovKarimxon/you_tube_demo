package com.company.dto;

import com.company.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProfileResponseDTO {

    private Integer id;
    private  String  name;
    private String surname;
    private String mainPhoto;
    private String email;
    private ProfileRole role;
    private String password;
}
