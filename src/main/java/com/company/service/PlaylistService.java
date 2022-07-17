package com.company.service;

import com.company.dto.ProfileDTO;
import com.company.dto.ResponseInfoDTO;
import com.company.dto.channel.ChannelDTO;
import com.company.dto.playlist.PlaylistCreateDTO;
import com.company.dto.playlist.PlaylistDTO;
import com.company.dto.playlist.PlaylistStatusDTO;
import com.company.dto.playlist.PlaylistUpdateDTO;
import com.company.entity.ChannelEntity;
import com.company.entity.PlaylistEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.exps.BadRequestException;
import com.company.exps.ItemNotFoundEseption;
import com.company.exps.NotPermissionException;
import com.company.mapper.PlaylistInfo;
import com.company.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AttachService attachService;

    public String create(PlaylistCreateDTO dto) {

        PlaylistEntity entity = new PlaylistEntity();

        entity.setName(dto.getName());
        entity.setChannelId(dto.getChannelId());
        entity.setDescription(dto.getDescription());
        entity.setOrders(dto.getOrder());
        playlistRepository.save(entity);

        return "successfully";
    }

    public PlaylistDTO update(PlaylistUpdateDTO dto) {

        PlaylistEntity entity = get(dto.getId());
        if (!entity.getChannel().getProfileId().equals(profileService.currentUser().getId())) {
            throw new BadRequestException("No access");
        }

        entity.setName(dto.getName());
        entity.setOrders(dto.getOrder());
        entity.setDescription(dto.getDescription());
        playlistRepository.save(entity);

        return getDTO(entity);
    }

    private PlaylistDTO getDTO(PlaylistEntity entity) {

        PlaylistDTO dto = new PlaylistDTO();
        dto.setId(entity.getId());
        dto.setOrder(entity.getOrders());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    private PlaylistEntity get(Integer playlistId) {

        return playlistRepository.findById(playlistId).orElseThrow(() -> {
            throw new ItemNotFoundEseption("palylist not fount");
        });
    }

    public String changeStatus(PlaylistStatusDTO dto) {

        PlaylistEntity entity = get(dto.getId());
        if (!entity.getChannel().getProfileId().equals(profileService.currentUser().getId())) {
            throw new BadRequestException("No access");
        }

        playlistRepository.changeStatus(dto.getStatus(), dto.getId());

        return "succesfully";

    }

    public ResponseInfoDTO changeVisible(Integer pId) {

        ProfileEntity profile = profileService.currentUser();
        PlaylistEntity playlist = get(pId);
        if (!profile.getRole().equals(ProfileRole.ROLE_ADMIN) &&
                !playlist.getChannel().getProfileId().equals(profile.getId())) {
            throw new NotPermissionException("No access");
        }

        playlist.setVisible(!playlist.getVisible());
        playlistRepository.save(playlist);

        return new ResponseInfoDTO(1, "success");
    }

    public PlaylistDTO playlistInfo(PlaylistInfo entity) {
        PlaylistDTO dto = new PlaylistDTO();
        dto.setId(entity.getId());
        dto.setOrder(entity.getOrders());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setName(entity.getName());

        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setId(entity.getChannelId());
        channelDTO.setName(entity.getChannelName());
        channelDTO.setPhotoId(attachService.getAttach(entity.getChannelAttachId()));

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(entity.getProfileId());
        profileDTO.setName(entity.getProfileName());
        profileDTO.setSurname(entity.getProfileSurname());
        profileDTO.setMainPhoto(attachService.getAttach(entity.getProfileAttachId()));

        channelDTO.setProfile(profileDTO);
        dto.setChannel(channelDTO);

        return dto;
    }


    public PlaylistDTO findById(Integer id) {
        PlaylistInfo entity = playlistRepository.fullInfo(id);
        PlaylistDTO playlistDTO = playlistInfo(entity);

        return playlistDTO;

    }

    public PageImpl<PlaylistDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PlaylistInfo> playlistInfoPage = playlistRepository.pagination(pageable);
        List<PlaylistDTO> dtoList = new LinkedList<>();
        playlistInfoPage.getContent().forEach(entity -> {
            dtoList.add(playlistInfo(entity));
        });
        return new PageImpl(dtoList, pageable, playlistInfoPage.getTotalElements());

    }
}
