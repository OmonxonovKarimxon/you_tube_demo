package com.company.service;

import com.company.dto.AttachDTO;
import com.company.dto.channel.ChannelDTO;
import com.company.dto.playlist.PlaylistDTO;
import com.company.dto.playlist.PlaylistUpdateDTO;
import com.company.dto.video.*;
import com.company.entity.*;
import com.company.exps.BadRequestException;
import com.company.exps.ItemNotFoundEseption;
import com.company.mapper.PlaylistInfo;
import com.company.mapper.VideoShortInfo;
import com.company.repository.PlaylistVideoRepository;
import com.company.repository.VideoRepository;
import com.company.repository.VideoTagRepository;
import com.company.repository.VideoWatchedRepository;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
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
public class VideoService {

    private final VideoRepository videoRepository;
    private final ProfileService profileService;
    private final AttachService attachService;
    private final TagService tagService;
    private final VideoTagRepository videoTagRepository;
    private final PlaylistVideoRepository playlistVideoRepository;
    private final VideoWatchedRepository videoWatchedRepository;

    public VideoService(VideoRepository videoRepository, ProfileService profileService, AttachService attachService, TagService tagService, VideoTagRepository videoTagRepository, PlaylistVideoRepository playlistVideoRepository, VideoWatchedRepository videoWatchedRepository) {
        this.videoRepository = videoRepository;
        this.profileService = profileService;
        this.attachService = attachService;
        this.tagService = tagService;
        this.videoTagRepository = videoTagRepository;
        this.playlistVideoRepository = playlistVideoRepository;
        this.videoWatchedRepository = videoWatchedRepository;
    }


    public String create(VideoCreateDTO dto) {
        VideoEntity entity = new VideoEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setAttachId(dto.getAttach());
        entity.setPreviewId(dto.getReview());
        entity.setChannelId(dto.getChannelId());

        videoRepository.save(entity);

        if (dto.getTags().size() != 0) {
            for (String tag : dto.getTags()) {

                TagEntity tagEntity = tagService.createdIfNotExist(tag);

                VideoTagEntity videoTag = new VideoTagEntity();
                videoTag.setVideoId(entity.getId());
                videoTag.setTagId(tagEntity.getId());
                videoTagRepository.save(videoTag);


            }
        }

        if (dto.getPlaylist() !=null) {

            PlaylistVideoEntity playlistVideo = new PlaylistVideoEntity();
            playlistVideo.setVideoId(entity.getId());
            playlistVideo.setPlaylistId(dto.getPlaylist());
            playlistVideoRepository.save(playlistVideo);
        }


        return "successfullly";
    }

    public String update(VideoUpdateDTO dto) {

        VideoEntity entity = get(dto.getId());

        if (!entity.getChannel().getProfileId().equals(profileService.currentUser().getId())) {
            throw new BadRequestException("No access");
        }
        videoRepository.update(dto.getDescription(),dto.getReview(),dto.getTitle());
return "successfully";
    }



    public String changeStatus(VideoStatusDTO dto) {
        VideoEntity entity = get(dto.getId());

        if (!entity.getChannel().getProfileId().equals(profileService.currentUser().getId())) {
            throw new BadRequestException("No access");
        }
        videoRepository.changeStatus(dto.getStatus());
        return "successfully";
    }
    public  String IncreaseVideo(String id) {
        VideoEntity entity = get(id);
         int count = entity.getViewCount()+1;
        videoRepository.IncreaseVideo(count);

        VideoWatchedEntity videoWatchedEntity = new VideoWatchedEntity();
        ProfileEntity profileEntity = profileService.currentUser();
        VideoEntity videoEntity = get(id);
        videoWatchedEntity.setVideo(videoEntity);
        videoWatchedEntity.setProfile(profileEntity);
        videoWatchedRepository.save(videoWatchedEntity);

        return "successfully";
    }

    public VideoShortInfoDTO shortInfo(VideoShortInfo entity){

        VideoShortInfoDTO dto = new VideoShortInfoDTO();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setCreatedDate(entity.getCreateDate());
        dto.setViewCount(entity.getViewCount());
        dto.setReview(attachService.getAttach(entity.getPreview()));

        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setId(entity.getChannelId());
        channelDTO.setName(entity.getChannelName());
        channelDTO.setPhotoId(attachService.getAttach(entity.getChannelAttachId()));

        dto.setChannel(channelDTO);
        return dto;


    }


    private VideoEntity get(String id){
        return videoRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundEseption("video not found");
        });
    }

    public VideoShortInfoDTO findByTitle(String title) {

        VideoShortInfo video = videoRepository.findByTitle(title);
        return shortInfo(video);
    }

    public PageImpl<VideoShortInfoDTO> pagination(int page, int size, Integer categoryId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VideoShortInfo> videoShortInfoPage = videoRepository.pagination(categoryId,pageable);
        List<VideoShortInfoDTO> dtoList = new LinkedList<>();
        videoShortInfoPage.getContent().forEach(entity -> {
            dtoList.add(shortInfo(entity));
        });
        return new PageImpl(dtoList, pageable, videoShortInfoPage.getTotalElements());

    }
}
