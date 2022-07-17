package com.company.repository;

import com.company.dto.playlist.PlaylistStatusDTO;
import com.company.entity.ChannelEntity;
import com.company.entity.PlaylistEntity;
import com.company.enums.PlaylistStatus;
import com.company.mapper.PlaylistInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;


public interface PlaylistRepository extends PagingAndSortingRepository<PlaylistEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update PlaylistEntity set status = ?1 where id = ?2")
    void changeStatus(PlaylistStatus status, Integer id);

    @Transactional
    @Modifying
    @Query("update PlaylistEntity set visible = false where id = ?1")
    void deletePlaylist(Integer id);

    @Query(value = "select p.id as id, p.name as name, p.description as description, p.status as status, p.orders as orders," +
            "         c.id as channelId, c.name as channelName, c.photo_id as channelAttachId, pr.id as profileId," +
            "     pr.name as profileName, pr.surname as profileSurname, pr.main_photo as profileAttachId " +
            "     from play_list p " +
            "     join channel c on p.channel_id = c.id " +
            "     join profile pr on c.profile_id = pr.id where p.id=:id", nativeQuery = true)
    PlaylistInfo fullInfo(Integer id);

    @Query(value = "select p.id as id, p.name as name, p.description as description, p.status as status, p.orders as orders," +
            "         c.id as channelId, c.name as channelName, c.photo_id as channelAttachId, pr.id as profileId," +
            "     pr.name as profileName, pr.surname as profileSurname, pr.main_photo as profileAttachId " +
            "     from play_list p " +
            "     join channel c on p.channel_id = c.id " +
            "     join profile pr on c.profile_id = pr.id", nativeQuery = true)
    Page<PlaylistInfo> pagination(Pageable pageable);
}
