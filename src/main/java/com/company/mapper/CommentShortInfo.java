package com.company.mapper;

import java.time.LocalDateTime;

public interface CommentShortInfo {

    Integer getId();

    String getContent();

    LocalDateTime getCreateDate();

    Integer getLikeCount();

    Integer getDislikeCount();

    String getVideoId();
    String getVideoName();

    String getPreviewId();


}

//  id,content,created_date,like_count,dislike_count, video(id,name,preview_attach_id,title,duration)
