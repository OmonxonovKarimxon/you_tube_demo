package com.company.dto.comment;

import com.company.dto.ProfileDTO;
import com.company.dto.video.VideoDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentFullInfoDTO {
    private Integer id;
    private ProfileDTO prifile;
    private Integer likeCount;
    private Integer dislikeCount;
    private String content;
    private LocalDateTime createDate;


}
