package com.company.service;

import com.company.dto.ProfileDTO;
import com.company.dto.ProfileResponseDTO;
import com.company.dto.RegistrationDTO;
import com.company.entity.AttachEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileStatus;
import com.company.exps.BadRequestException;
import com.company.exps.ItemNotFoundEseption;
import com.company.repository.ProfileRepository;
import com.company.util.MD5Util;
import com.company.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final AttachService attachService;

    @Value("${server.url}")
    private String serverUrl;

    public ProfileService(ProfileRepository profileRepository, AttachService attachService) {
        this.profileRepository = profileRepository;
        this.attachService = attachService;
    }


    //admin create qiladi faqat
    public void create(RegistrationDTO dto) {

        ProfileEntity profile = new ProfileEntity();

        profile.setEmail(dto.getEmail());
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        profile.setPassword(dto.getPassword());
        profile.setRole(dto.getRole());
        profile.setVisible(Boolean.TRUE);
        profile.setStatus(ProfileStatus.ACTIVE);
        profile.setRole(dto.getRole());

        if (dto.getMainPhoto() != null) {
            AttachEntity mainPhoto = new AttachEntity(dto.getMainPhoto());
            profile.setMainPhoto(mainPhoto);
        }
        profileRepository.save(profile);
    }


    public void changePassword(String password) {

        ProfileEntity profile = currentUser();

        profileRepository.changePassword(MD5Util.getMd5(password), profile.getEmail());


    }


    public void changeNameSurname(ProfileDTO profileDto) {

        profileRepository.changeNameSurname(profileDto.getName(), profileDto.getSurname(), profileDto.getEmail());
    }

    public ProfileResponseDTO getProfileDetail() {
        ProfileEntity profileEntity = currentUser();
        Optional<ProfileEntity> optional = profileRepository.findByEmail(profileEntity.getEmail());
        if (optional.isEmpty()) {
            throw new BadRequestException("khsbcid isdubcisd isdbcids");
        }

        return entityToDto(optional.get());

    }


    private ProfileResponseDTO entityToDto(ProfileEntity entity) {

        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setId(entity.getId());
        dto.setMainPhoto(serverUrl + "attach/open/" + entity.getMainPhoto().getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        return dto;

    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundEseption("Profile Not found");
        });
    }

    public ProfileEntity currentUser() {

        return SpringUtil.currentUser().getProfile();
    }

    //  id,profile(id,name,surname,photo(id,url))
    public ProfileDTO profileInfo(ProfileEntity entity) {

        ProfileDTO dto = new ProfileDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setMainPhoto(attachService.getAttach(entity.getPhotoId()));
        return dto;
    }
}
