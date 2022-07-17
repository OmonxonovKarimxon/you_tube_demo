package com.company.dto.video;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class VideoCreateDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String attach;

    private String review;
    @NotBlank
    private String channelId;

    private Integer playlist;

    private List<String> tags;
}
