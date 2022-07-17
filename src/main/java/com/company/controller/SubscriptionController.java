package com.company.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Subscription")
public class Subscription {

    @PostMapping("/create")
    public ResponseEntity<?> create(){

    }
}
