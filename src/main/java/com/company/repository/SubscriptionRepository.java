package com.company.repository;

import com.company.entity.SubscriptionEntity;
import com.company.enums.NotificationType;
import com.company.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends PagingAndSortingRepository<SubscriptionEntity, Integer> {


    Optional<SubscriptionEntity> findByChannelIdAndProfileId(String channelId, Integer profileId);

    Iterable<SubscriptionEntity> findByProfileIdAndStatus( Integer profileId,SubscriptionStatus status);

    @Modifying
    @Transactional
    @Query("update SubscriptionEntity  set  type=:type  where channelId=:channelId and profileId=:profileId")
    void updateNotification(String channelId, Integer profileId, NotificationType type);

    @Modifying
    @Transactional
    @Query("update SubscriptionEntity  set  status=:status  where channelId=:channelId and profileId=:profileId")
    void updatedStatus(String channelId, Integer profileId, SubscriptionStatus status);

}
