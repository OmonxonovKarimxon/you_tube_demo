package com.company.service;


import com.company.dto.channel.ChannelDTO;
import com.company.dto.video.VideoDTO;
import com.company.dto.video.VideoLikeCreateDTO;
import com.company.dto.video.VideoLikeDTO;
import com.company.entity.ProfileEntity;
import com.company.entity.VideoEntity;
import com.company.entity.VideoLikeEntity;
import com.company.enums.LikeStatus;
import com.company.exps.ItemNotFoundEseption;
import com.company.mapper.VideoLikeInfo;
import com.company.repository.VideoLikeRepository;
import com.company.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoLikeService {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private VideoLikeRepository videoLikeRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private AttachService attachService;

    public void videoLike(VideoLikeCreateDTO dto) {
        ProfileEntity profileEntity = profileService.currentUser();

        likeDislike(dto.getVideoId(), profileEntity.getId(), LikeStatus.LIKE);
    }

    public void videoDislike(VideoLikeCreateDTO dto) {
        ProfileEntity profileEntity = profileService.currentUser();
        likeDislike(dto.getVideoId(), profileEntity.getId(), LikeStatus.DISLIKE);
    }

    private void likeDislike(String videoId, Integer pId, LikeStatus status) {
        Optional<VideoLikeEntity> optional = videoLikeRepository.findExists(videoId, pId);
        if (optional.isPresent()) {
            VideoLikeEntity like = optional.get();
            like.setStatus(status);
            videoLikeRepository.save(like);
            return;
        }

        boolean b = videoRepository.existsById(videoId);
        if (!b) {
            throw new ItemNotFoundEseption("video Not Found");
        }
        VideoLikeEntity entity = new VideoLikeEntity();
        entity.setVideoId(videoId);
        entity.setProfileId(pId);
        entity.setStatus(status);
        videoLikeRepository.save(entity);

    }

    public void removeLike( VideoLikeCreateDTO dto) {
        ProfileEntity profileEntity = profileService.currentUser();
     videoLikeRepository.delete(dto.getVideoId(), profileEntity.getId());

    }
    public VideoLikeDTO likeInfo(VideoLikeInfo entity){
        VideoLikeDTO dto = new VideoLikeDTO();
        dto.setId(entity.getId());

        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setId(entity.getVideoId());
        videoDTO.setName(entity.getChannelName());

        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setId(entity.getChannelId());
        channelDTO.setName(entity.getChannelName());
        videoDTO.setChannel(channelDTO);

        videoDTO.setAttach(attachService.getAttach(entity.getVideoPreview()));

        dto.setVideoDTO(videoDTO);

        return dto;
    }


    public List<VideoLikeDTO> videoListByLiked() {
        ProfileEntity profileEntity = profileService.currentUser();
       List<VideoLikeInfo> videoLikeInfos= videoLikeRepository.videoListByLiked(profileEntity.getId());
       List<VideoLikeDTO> dtoList = new LinkedList<>();
        videoLikeInfos.forEach(entity->{
            dtoList.add(likeInfo(entity));
        });
        return  dtoList;
    }
}
