package com.company.repository;

import com.company.entity.VideoLikeEntity;
import com.company.mapper.VideoLikeInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface VideoLikeRepository extends PagingAndSortingRepository<VideoLikeEntity, Integer> {


    @Query("FROM VideoLikeEntity v where  v.videoId=:videoId and v.profileId =:profileId")
    Optional<VideoLikeEntity> findExists(String videoId, Integer profileId);

    @Transactional
    @Modifying
    @Query("delete from VideoLikeEntity  where videoId=?1 and profileId=?2")
    void delete(String videoId, Integer pId);

    @Query(value = "select vl.id as id,  " +
            "  c.id as channelId, c.name as channelName, v.preview_attach_id as videoPreview " +
            " from video_like vl " +
            " join video v on v.id = vl.video_id " +
            " join channel c on v.channel_id = c.id " +
            " where  vl.profile_id=:profileId " +
            " order by created_date desc ", nativeQuery = true)
    List<VideoLikeInfo> videoListByLiked(Integer profileId);

}
