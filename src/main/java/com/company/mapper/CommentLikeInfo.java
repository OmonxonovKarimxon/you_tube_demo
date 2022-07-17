package com.company.mapper;

import com.company.enums.LikeStatus;

import java.time.LocalDateTime;

public interface CommentLikeInfo {

    //    id,profile_id,comment_id,created_date,type(Like,Dislike)
    Integer getId();

    Integer getProfileId();

    Integer getCommentId();

    LocalDateTime getCreateDate();

    LikeStatus getStatus();

}
