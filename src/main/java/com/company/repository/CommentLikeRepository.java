package com.company.repository;

import com.company.entity.CommentLikeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends PagingAndSortingRepository<CommentLikeEntity, Integer> {


    @Query("FROM CommentLikeEntity c where  c.commentId=:commentId and c.profileId =:profileId")
    Optional<CommentLikeEntity> findExists(Integer commentId, Integer profileId);

    @Transactional
    @Modifying
    @Query("delete from CommentLikeEntity  where commentId=?1 and profileId=?2")
    void delete(Integer commentId, Integer pId);

    @Query("FROM CommentLikeEntity  where  profileId=:profileId")
    List<CommentLikeEntity> videoListByLiked(Integer profileId);

}
