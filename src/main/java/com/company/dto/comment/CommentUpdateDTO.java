package com.company.dto.comment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentUpdateDTO {
    @NotNull(message = "content id null")
    private  Integer id;
    @NotNull(message = "content id null")
    private String content;

}
