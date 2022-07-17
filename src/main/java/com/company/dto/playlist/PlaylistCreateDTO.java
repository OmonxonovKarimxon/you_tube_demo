package com.company.dto.playlist;

import com.company.enums.PlaylistStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaylistCreateDTO {

    private String name;
    private Integer order;
    private String channelId;
    private String description;
    private PlaylistStatus status;
}
