package com.company.service;


import com.company.dto.channel.ChannelDTO;
import com.company.dto.comment.CommentLikeCreateDTO;
import com.company.dto.comment.CommentLikeDTO;
import com.company.dto.video.VideoDTO;
import com.company.dto.video.VideoLikeCreateDTO;
import com.company.dto.video.VideoLikeDTO;
import com.company.entity.CommentLikeEntity;
import com.company.entity.ProfileEntity;
import com.company.entity.VideoLikeEntity;
import com.company.enums.LikeStatus;
import com.company.exps.ItemNotFoundEseption;
import com.company.mapper.VideoLikeInfo;
import com.company.repository.CommentLikeRepository;
import com.company.repository.CommentRepository;
import com.company.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentLikeService {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private CommentLikeRepository commentLikeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AttachService attachService;

    public void commentLike(CommentLikeCreateDTO dto) {
        ProfileEntity profileEntity = profileService.currentUser();

        likeDislike(dto.getCommentId(), profileEntity.getId(), LikeStatus.LIKE);
    }

    public void commentDislike(CommentLikeCreateDTO dto) {
        ProfileEntity profileEntity = profileService.currentUser();
        likeDislike(dto.getCommentId(), profileEntity.getId(), LikeStatus.DISLIKE);
    }

    private void likeDislike(Integer commentId, Integer pId, LikeStatus status) {
        Optional<CommentLikeEntity> optional = commentLikeRepository.findExists(commentId, pId);
        if (optional.isPresent()) {
            CommentLikeEntity like = optional.get();
            like.setStatus(status);
            commentLikeRepository.save(like);
            return;
        }

        boolean b = commentRepository.existsById(commentId);
        if (!b) {
            throw new ItemNotFoundEseption("comment Not Found");
        }
        CommentLikeEntity entity = new CommentLikeEntity();
        entity.setCommentId(commentId);
        entity.setProfileId(pId);
        entity.setStatus(status);
        commentLikeRepository.save(entity);

    }

    public void removeLike(CommentLikeCreateDTO dto) {
        ProfileEntity profileEntity = profileService.currentUser();
        commentLikeRepository.delete(dto.getCommentId(), profileEntity.getId());

    }

    public CommentLikeDTO likeInfo(CommentLikeEntity entity) {
        CommentLikeDTO dto = new CommentLikeDTO();
        dto.setId(entity.getId());
        dto.setCommentId(entity.getCommentId());
        dto.setCreateDate(entity.getCreatedDate());
        dto.setStatus(entity.getStatus());
        dto.setProfileId(entity.getProfileId());


        return dto;
    }


    public List<CommentLikeDTO> commentListByLiked() {
        ProfileEntity profileEntity = profileService.currentUser();
        List<CommentLikeEntity> entityList = commentLikeRepository.videoListByLiked(profileEntity.getId());
        List<CommentLikeDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(likeInfo(entity));
        });
        return dtoList;
    }
}
