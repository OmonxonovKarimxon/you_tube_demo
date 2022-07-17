package com.company.dto.subscription;

import com.company.enums.NotificationType;
import com.company.enums.SubscriptionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
 public class SubscriptionChangeNotificationDTO {
    @NotNull(message = "channel id is not be null")
    private String ChannelId;
    @NotNull
    private NotificationType type;
}
