package com.company.dto.video;

import com.company.enums.PlaylistStatus;
import com.company.enums.VideoStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoStatusDTO {

    private  String id;
    private VideoStatus status;

}
