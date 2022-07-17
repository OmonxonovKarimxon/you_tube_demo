package com.company.repository;

import com.company.entity.VideoEntity;
import com.company.enums.VideoStatus;
import com.company.mapper.VideoLikeInfo;
import com.company.mapper.VideoShortInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface VideoRepository extends PagingAndSortingRepository<VideoEntity, String> {

    @Modifying
    @Transactional
    @Query("update VideoEntity  v set v.description=:description, v.previewId=:review, v.title=:title ")
    void update(String description, String review, String title);

    @Modifying
    @Transactional
    @Query("update VideoEntity  v set v.status=:status ")
    void changeStatus(VideoStatus status);


    @Modifying
    @Transactional
    @Query("update VideoEntity  v set v.viewCount=:count ")
    void IncreaseVideo(int count);

    @Query(value = "select v.id as id, v.title as title, v.preview_attach_id as preview, v.created_date as createdDate, " +
            "         c.id as channelId, c.name as channelName, c.photo_id as channelAttachId " +
            " from video v " +
            " join channel c on v.channel_id = c.id " +
            " where  v.title=:title ", nativeQuery = true)
    VideoShortInfo findByTitle(String title);

    @Query(value = "select v.id as id, v.title as title, v.preview_attach_id as preview, v.created_date as createdDate, " +
            "         c.id as channelId, c.name as channelName, c.photo_id as channelAttachId " +
            " from video v " +
            " join channel c on v.channel_id = c.id " +
            " where  v.category_id=:categoryId ", nativeQuery = true)
    Page<VideoShortInfo> pagination(Integer categoryId, Pageable pageable);

}
//    Integer getId();
//
//    String getVideoId();
//
//    String getVideoPreview();
//
//    String getChannelId();
//
//
//    String getChannelName();