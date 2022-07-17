package com.company.mapper;

import java.time.LocalDateTime;

public interface VideoShortInfo {


    String getId();
    String getTitle();
    String getPreview();
    LocalDateTime getCreateDate();
    String getChannelId();
    String getChannelAttachId();
    String getChannelName();
    Integer getViewCount();

//    VideShortInfo(id,key,title, preview_attach(id,url),
//    published_date, channel(id,name,photo(url)),
}
