package com.company.mapper;

import java.time.LocalDateTime;

public interface CommentFullInfo {

    Integer getId();

    String getContent();

    LocalDateTime getCreateDate();

    Integer getLikeCount();

    Integer getDislikeCount();

    Integer getProfileId();

    String getProfileName();

    String getProfileSurname();

    String getProfileAttachId();

}

