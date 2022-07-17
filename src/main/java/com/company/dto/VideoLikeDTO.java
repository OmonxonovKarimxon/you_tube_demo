package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VideoLikeDTO {
    @NotNull(message = "id is not be null")
    private String videoId;
    private Integer likeCount = 0;
    private Integer dislikeCount = 0;
}
