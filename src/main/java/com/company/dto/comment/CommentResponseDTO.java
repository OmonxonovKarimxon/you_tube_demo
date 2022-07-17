package com.company.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDTO {
    private Integer id;
    private String videoId;
    private Integer replyId;
    private String content;
    private Integer profileId;
    private LocalDateTime createDate;


}
