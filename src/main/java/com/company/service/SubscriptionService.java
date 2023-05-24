package com.company.service;


import com.company.dto.subscription.SubscriptionChangeNotificationDTO;
import com.company.dto.subscription.SubscriptionChangeStatusDTO;
import com.company.dto.subscription.SubscriptionCreateDTO;
import com.company.dto.subscription.SubscriptionInfoDTO;
import com.company.entity.ProfileEntity;
import com.company.entity.SubscriptionEntity;
import com.company.enums.SubscriptionStatus;
import com.company.exps.ItemNotFoundEseption;
import com.company.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final ProfileService profileService;
    private final ChannelService channelService;


    public SubscriptionService(SubscriptionRepository subscriptionRepository, ProfileService profileService, ChannelService channelService) {
        this.subscriptionRepository = subscriptionRepository;
        this.profileService = profileService;
        this.channelService = channelService;
    }

    public String create(SubscriptionCreateDTO dto) {
        ProfileEntity profileEntity = profileService.currentUser();
        SubscriptionEntity entity = new SubscriptionEntity();
        entity.setChannelId(dto.getChannelId());
        entity.setProfileId(profileEntity.getId());
        subscriptionRepository.save(entity);

        return "SUCCESSFULLY";
    }

    public String changeStatus(SubscriptionChangeStatusDTO dto) {
        ProfileEntity profileEntity = profileService.currentUser();

        SubscriptionEntity entity = new SubscriptionEntity();
        Optional<SubscriptionEntity> optional = subscriptionRepository.findByChannelIdAndProfileId(dto.getChannelId(), profileEntity.getId());
        if (!optional.isPresent()) {
            throw new ItemNotFoundEseption("first!!! you subscribe this channel do you understand me");
        }

        subscriptionRepository.updatedStatus(dto.getChannelId(), profileEntity.getId(), dto.getStatus());

        return "SUCCESSFULLY";
    }

    public String changeNotification(SubscriptionChangeNotificationDTO dto) {
        ProfileEntity profileEntity = profileService.currentUser();

        SubscriptionEntity entity = new SubscriptionEntity();
        Optional<SubscriptionEntity> optional = subscriptionRepository.findByChannelIdAndProfileId(dto.getChannelId(), profileEntity.getId());
        if (!optional.isPresent()) {
            throw new ItemNotFoundEseption("first!!! you subscribe this channel do you understand me");
        }

        subscriptionRepository.updateNotification(dto.getChannelId(), profileEntity.getId(), dto.getType());

        return "SUCCESSFULLY";
    }

    public List<SubscriptionInfoDTO> list() {
        ProfileEntity profileEntity = profileService.currentUser();
        Iterable<SubscriptionEntity> list = subscriptionRepository.findByProfileIdAndStatus(profileEntity.getId(), SubscriptionStatus.ACTIVE);
        List<SubscriptionInfoDTO> dtoList = new ArrayList<>();
        list.forEach(entity -> {
            dtoList.add(subscriptionInfo(entity));
        });
        return dtoList;
    }

    private SubscriptionInfoDTO subscriptionInfo(SubscriptionEntity entity) {
        SubscriptionInfoDTO dto = new SubscriptionInfoDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setChannel(channelService.channelInfo(entity.getChannel()));
        return dto;
    }

    public List<SubscriptionInfoDTO> listForAdmin(Integer profileId) {

        Iterable<SubscriptionEntity> list = subscriptionRepository.findByProfileIdAndStatus(profileId, SubscriptionStatus.ACTIVE);
        List<SubscriptionInfoDTO> dtoList = new ArrayList<>();
        list.forEach(entity -> {
            SubscriptionInfoDTO dto = subscriptionInfo(entity);
            dto.setCreateDate(entity.getCreatedDate());
            dtoList.add(dto);
        });
        return dtoList;

    }
}
