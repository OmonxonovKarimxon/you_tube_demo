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
public class VideoDTO {


    private String id;
    private String name;
    private String attachId;
    private AttachDTO attach;
    private String reviewId;
    private AttachDTO review;
    private String channelId;
    private ChannelDTO channel;
    private LocalDateTime createdDate;
    private Integer time;
    private Integer sharedCount;
    private Boolean visible;
    private VideoStatus status;

}
