package com.company.service;

import com.company.config.CustomUserDetails;
import com.company.dto.AuthDTO;
import com.company.dto.ProfileDTO;
import com.company.dto.RegistrationDTO;
import com.company.dto.ResponseInfoDTO;
import com.company.entity.AttachEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exps.BadRequestException;
import com.company.repository.ProfileRepository;
import com.company.util.JwtUtil;
import com.company.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;
    @Autowired
    private  ProfileService profileService;

    public ProfileDTO login(AuthDTO authDTO) {

        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(), MD5Util.getMd5(authDTO.getPassword())));
        CustomUserDetails user = (CustomUserDetails) authenticate.getPrincipal();
        ProfileEntity profile = user.getProfile();

        ProfileDTO dto = new ProfileDTO();
        dto.setEmail(profile.getEmail());
        dto.setJwt(JwtUtil.encode(profile.getId()));


        return dto;
    }


    //user ozi registration dan o'tishi
    public String registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            throw new BadRequestException("User already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.getMd5(dto.getPassword()));
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setStatus(ProfileStatus.BLOCKED);
        entity.setRole(ProfileRole.ROLE_USER);

        if (dto.getMainPhoto() != null) {
            AttachEntity mainPhoto = new AttachEntity(dto.getMainPhoto());
            entity.setMainPhoto(mainPhoto);
        }
        profileRepository.save(entity);

     //   emailService.sendRegistrationEmail(entity.getEmail(), entity.getId());

        return "Successfully";
    }

    public void updateEmail(String newEmail) {

        ProfileEntity entity = profileService.currentUser();

        emailService.sendRegistrationEmail(newEmail, entity.getId());
    }
    public ResponseInfoDTO updateEmailVerification(Integer profileId) {
        ProfileEntity profile = profileService.get(profileId);

        profileRepository.updateEmail(profile.getTempEmail(), profileId);
        return  new ResponseInfoDTO(1,"successfully");
    }

    public String emailVerification(Integer id) {
        ProfileEntity profile = profileService.get(id);

        profile.setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(profile);
        return "<h1 style='align-text:center'>Success. Tabriklaymiz.</h1>";
    }


}
