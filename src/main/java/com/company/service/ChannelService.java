package com.company.service;

import com.company.dto.channel.ChannelCreateDTO;
import com.company.dto.channel.ChannelDTO;
import com.company.entity.ChannelEntity;
import com.company.entity.ProfileEntity;
import com.company.exps.ItemNotFoundEseption;
import com.company.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AttachService attachService;


    public String create(ChannelCreateDTO dto) {

        ProfileEntity profileEntity = profileService.currentUser();

        ChannelEntity entity = new ChannelEntity();
        entity.setPhotoId(dto.getPhotoId());
        entity.setProfileId(profileEntity.getId());
        entity.setBannerId(dto.getBannerId());
        entity.setName(dto.getName());
        channelRepository.save(entity);
        return "successfully";

    }

    public String update(ChannelDTO dto) {
        ProfileEntity profileEntity = profileService.currentUser();
        Optional<ChannelEntity> optional = channelRepository.findByIdAndVisible(dto.getId(), true);

        if (optional.isEmpty()||optional.get().getProfileId()!=profileEntity.getId()) {
            throw new ItemNotFoundEseption("you have not this channel");
        }
        ChannelEntity entity = optional.get();
        if(dto.getName() != null){
            entity.setName(dto.getName());
        }if(dto.getBannerId() != null){
            entity.setBannerId(dto.getBannerId().getId());
        }if(dto.getPhotoId() != null){
            entity.setProfileId(dto.getProfile().getId());
        }
        channelRepository.save(entity);

        return "successfully";
    }

    public PageImpl<ChannelDTO> pagination(int page, int size) {
          Pageable pageable = PageRequest.of(page, size);
            Page<ChannelEntity> channelPage = channelRepository.pagination(pageable);
            List<ChannelDTO> dtoList = new LinkedList<>();
            channelPage.getContent().forEach(channel -> {
                dtoList.add(entityToDTO(channel));
            });
            return  new PageImpl(dtoList,pageable, channelPage.getTotalElements());

    }

    private ChannelDTO entityToDTO(ChannelEntity entity){
        ProfileEntity profileEntity = profileService.currentUser();
        ChannelDTO dto = new ChannelDTO();
         dto.setId(entity.getId());
         dto.setName(entity.getName());
         dto.setBannerId(attachService.entityToDto(entity.getBanner()));
         dto.setPhotoId(attachService.entityToDto(entity.getAttach()));


        return dto;
    }

    public ChannelDTO getById(String id) {
        Optional<ChannelEntity> optional = channelRepository.findByIdAndVisible(id, true);

        if (optional.isEmpty()) {
            throw new ItemNotFoundEseption("you have not this channel");
        }
        return entityToDTO(optional.get());
    }

    public List<ChannelDTO> list() {
        ProfileEntity profileEntity = profileService.currentUser();
        List<ChannelEntity> entityList = channelRepository.findByProfileIdAndVisible(profileEntity.getId(), true);
        List<ChannelDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(entityToDTO(entity));
        });

        return dtoList;
    }



    public ChannelEntity get(String id) {
        return channelRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundEseption("This channel not found");
        });
    }
    public ChannelDTO channelInfo(ChannelEntity entity){
        ProfileEntity profileEntity = profileService.currentUser();
        ChannelDTO dto = new ChannelDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhotoId(attachService.getAttach(entity.getPhotoId()));
        return dto;
    }


}
