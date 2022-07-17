package com.company.dto.comment;

import com.company.dto.video.VideoDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentLikeCreateDTO {


    private  Integer commentId;

}
