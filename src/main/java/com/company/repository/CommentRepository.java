package com.company.repository;

import com.company.entity.CommentEntity;
import com.company.mapper.CommentFullInfo;
import com.company.mapper.CommentShortInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<CommentEntity, Integer> {


    @Transactional
    @Modifying
    @Query("UPDATE CommentEntity  c set c.content=:content where c.id=:id")
    void update(String content, Integer id);


    @Query(value = "select c.id as id, c.content as content,c.created_date as createDate, " +
            "            (select count(*) from comment_like  where profile_id =:profileId and status = 'LIKE') as likeCount, " +
            "            (select count(*) from comment_like  where profile_id =:profileId and status = 'DISLIKE' ) as dislikeCount," +
            "            v.id as videoId, v.title as videoName,v.preview_attach_id as previewId " +
            "             from comment c inner join video as v  on v.id=c.video_id " +
            "           where  c.profile_id=:profileId", nativeQuery = true)
    List<CommentShortInfo> commentListByProfileId(Integer profileId);

    @Query(value = "select c.id as id, c.content as content,c.created_date as createDate, " +
            "            (select count(*) from comment_like  where comment_id =c.id and status = 'LIKE') as likeCount, " +
            "            (select count(*) from comment_like  where comment_id =c.id and status = 'DISLIKE' ) as dislikeCount," +
            "     pr.name as profileName, pr.surname as profileSurname, pr.main_photo as profileAttachId " +
            "            from comment c " +
            "            inner join profile as pr on pr.id=c.profile_id " +
            "            where  c.video_id=:videoId", nativeQuery = true)
    List<CommentFullInfo> commentList(String videoId);
}
