package com.company.dto.channel;

import com.company.dto.AttachDTO;
import com.company.dto.ProfileDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter

public class ChannelDTO {

    private String id;
    private String name;
    private AttachDTO bannerId;
    private AttachDTO photoId;
    private ProfileDTO profile;



}
