package com.company.dto.video;

import com.company.dto.AttachDTO;
import com.company.dto.channel.ChannelDTO;
import com.company.enums.VideoStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class VideoShortInfoDTO {

    private String id;
    private String title;
    private AttachDTO review;
    private ChannelDTO channel;
    private LocalDateTime createdDate;
    private Integer viewCount;




}
