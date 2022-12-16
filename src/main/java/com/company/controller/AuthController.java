package com.company.controller;

import com.company.dto.AuthDTO;
import com.company.dto.ProfileDTO;
import com.company.dto.RegistrationDTO;
import com.company.dto.ResponseInfoDTO;
import com.company.dto.email.EmailUpdateDTO;
import com.company.service.AuthService;
import com.company.util.JwtUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = "Authorization and Registration")
@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/public/registration")
    public ResponseEntity<?> registration(@RequestBody @Valid RegistrationDTO dto) {

        String response = authService.registration(dto);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/public/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody @Valid AuthDTO dto) {


        ProfileDTO profileDto = authService.login(dto);
        return ResponseEntity.ok().body(profileDto);
    }

    @PutMapping("/public/email/updateEmail")
    public ResponseEntity<Void> updateEmail(@RequestBody EmailUpdateDTO dto) {
        authService.updateEmail(dto.getEmail());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/public/email/verification/{jwt}")
    public ResponseEntity<String> verification(@PathVariable("jwt") String jwt) {
        String response = authService.emailVerification(JwtUtil.decode(jwt));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/email/update/verification/{jwt}")
    public ResponseEntity<?> updateEmailVerification(@PathVariable("jwt") String jwt) {
        ResponseInfoDTO response = authService.updateEmailVerification(JwtUtil.decode(jwt));

        return ResponseEntity.ok(response);
    }
}
