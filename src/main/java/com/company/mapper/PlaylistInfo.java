package com.company.mapper;

import com.company.entity.AttachEntity;
import com.company.enums.PlaylistStatus;

public interface PlaylistInfo {

   Integer getId();
   String getName();
   String getDescription();
   PlaylistStatus getStatus();
   Integer getOrders();

   String getChannelId();
   String getChannelName();
   String getChannelAttachId();


   Integer getProfileId();
   String getProfileName();
   String getProfileSurname();
   String getProfileAttachId();






}

//id,name,description,status(private,public),order_num,

//channel(id,name,photo(id,url), profile(id,name,surname,photo(id,url)))
