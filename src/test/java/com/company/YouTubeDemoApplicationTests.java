package com.company;

import com.company.entity.SubscriptionEntity;
import com.company.enums.NotificationType;
import com.company.repository.SubscriptionRepository;
import com.company.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class YouTubeDemoApplicationTests {
@Autowired
private SubscriptionRepository subscriptionRepository;
    @Test
    void contextLoads() {

    }

}
