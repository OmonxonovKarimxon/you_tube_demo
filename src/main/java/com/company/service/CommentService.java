package com.company.service;

import com.company.dto.ProfileDTO;
import com.company.dto.comment.CommentCreateDTO;
import com.company.dto.comment.CommentFullInfoDTO;
import com.company.dto.comment.CommentShortInfoDTO;
import com.company.dto.comment.CommentUpdateDTO;
import com.company.dto.video.VideoDTO;
import com.company.entity.CommentEntity;
import com.company.entity.ProfileEntity;
import com.company.exps.ItemNotFoundEseption;
import com.company.mapper.CommentFullInfo;
import com.company.mapper.CommentShortInfo;
import com.company.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AttachService attachService;

    public String create(CommentCreateDTO dto) {
        ProfileEntity profileEntity = profileService.currentUser();
        CommentEntity entity = new CommentEntity();
        entity.setCommentId(dto.getReplyId());
        entity.setContent(dto.getContent());
        entity.setProfileId(profileEntity.getId());
        entity.setVideoId(dto.getVideoId());
        commentRepository.save(entity);


        return "successfully";
    }

    public String update(CommentUpdateDTO dto) {
        ProfileEntity profileEntity = profileService.currentUser();
        CommentEntity entity = get(dto.getId());

        if (!profileEntity.getId().equals(entity.getProfileId())) {

            throw new ItemNotFoundEseption("comment not found");
        }
        commentRepository.update(dto.getContent(), dto.getId());

        return "successfully";

    }

    private CommentEntity get(Integer id) {
        return commentRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundEseption("comment not found");
        });
    }

    public   List<CommentShortInfoDTO>   commentListByProfileId(Integer profileId) {
        List<CommentShortInfo> commentShortInfos = commentRepository.commentListByProfileId(profileId);
        List<CommentShortInfoDTO> dtoList = new ArrayList<>();
        commentShortInfos.forEach(entity -> {
            dtoList.add(shortInfo(entity));
        });
        return dtoList;
    }

    public CommentShortInfoDTO shortInfo(CommentShortInfo entity) {
        CommentShortInfoDTO dto = new CommentShortInfoDTO();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setContent(entity.getContent());
        dto.setDislikeCount(entity.getDislikeCount());
        dto.setLikeCount(entity.getLikeCount());

        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setId(entity.getVideoId());
        videoDTO.setName(entity.getVideoName());
        videoDTO.setReviewId(entity.getPreviewId());
        dto.setVideo(videoDTO);
        return dto;
    }

    public List<CommentFullInfoDTO> commentList(String videoId) {
        ProfileEntity profileEntity = profileService.currentUser();
        List<CommentFullInfo> commentFullInfos = commentRepository.commentList(videoId);
        List<CommentFullInfoDTO> dtoList = new ArrayList<>();
        commentFullInfos.forEach(entity -> {
            dtoList.add(commetFullInfo(entity));
        });
        return dtoList;

    }
    public CommentFullInfoDTO commetFullInfo(CommentFullInfo entity) {
        CommentFullInfoDTO dto = new CommentFullInfoDTO();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setContent(entity.getContent());
        dto.setDislikeCount(entity.getDislikeCount());
        dto.setLikeCount(entity.getLikeCount());
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(entity.getProfileName());
        profileDTO.setSurname(entity.getProfileSurname());
        profileDTO.setMainPhoto(attachService.getAttach(entity.getProfileAttachId()));
        dto.setPrifile(profileDTO);

        return dto;
    }
}
