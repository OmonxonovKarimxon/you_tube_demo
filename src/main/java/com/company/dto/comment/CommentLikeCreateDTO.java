package com.company.dto.video;

import com.company.dto.ProfileDTO;
import com.company.enums.LikeStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoLikeDTO {

    private Integer id;
    private VideoDTO videoDTO;

}
