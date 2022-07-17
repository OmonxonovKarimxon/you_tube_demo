package com.company.dto.comment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentCreateDTO {
    @NotNull(message = "video id null")
    private String videoId;
    private Integer replyId;
    @NotNull(message = "content id null")
    private String content;

}
