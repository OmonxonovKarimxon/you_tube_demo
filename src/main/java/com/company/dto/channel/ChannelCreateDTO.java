package com.company.dto.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter

public class ChannelCreateDTO {

    @NotNull
    private String name;
    private String bannerId;
    private String photoId;


}
