package com.company.controller;


import com.company.dto.subscription.SubscriptionChangeNotificationDTO;
import com.company.dto.subscription.SubscriptionChangeStatusDTO;
import com.company.dto.subscription.SubscriptionCreateDTO;
import com.company.dto.subscription.SubscriptionInfoDTO;
import com.company.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/user/create")
    public ResponseEntity<String> create(@RequestBody @Valid SubscriptionCreateDTO dto) {

        String response = subscriptionService.create(dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/user/changeStatus")
    public ResponseEntity<String> changeStatus(@RequestBody @Valid SubscriptionChangeNotificationDTO dto) {

        String response = subscriptionService.changeNotification(dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/user/changeNotification")
    public ResponseEntity<String> changeNotification(@RequestBody @Valid SubscriptionChangeStatusDTO dto) {

        String response = subscriptionService.changeStatus(dto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/user/list")
    public ResponseEntity<List<SubscriptionInfoDTO>> list() {

        List<SubscriptionInfoDTO> list = subscriptionService.list();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/adm/{profileId}")
    public ResponseEntity<List<SubscriptionInfoDTO>> list(@PathVariable("profileId") Integer profileId) {

        List<SubscriptionInfoDTO> list = subscriptionService.listForAdmin(profileId);
        return ResponseEntity.ok().body(list);
    }
}