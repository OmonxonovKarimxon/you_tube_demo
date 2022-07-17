package com.company.dto.playlist;

import com.company.enums.PlaylistStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaylistStatusDTO {

    private  Integer id;
    private PlaylistStatus status;

}
