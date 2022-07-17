package com.company.dto.subscription;

import com.company.dto.channel.ChannelDTO;
import com.company.enums.NotificationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
 public class SubscriptionInfoDTO {

    private Integer id;

    @NotNull(message = "channel id is not be null")
    private ChannelDTO channel;

    private NotificationType type;
    private LocalDateTime createDate;
}
