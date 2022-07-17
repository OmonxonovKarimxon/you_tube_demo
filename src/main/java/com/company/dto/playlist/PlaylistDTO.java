package com.company.dto.playlist;

import com.company.dto.channel.ChannelDTO;
import com.company.enums.PlaylistStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaylistDTO {

    private  Integer id;
    private String name;
    private ChannelDTO channel;
    private Integer order;
    private PlaylistStatus status;
    private Boolean visible;
    private LocalDateTime createdDate;
    private String description;

}
