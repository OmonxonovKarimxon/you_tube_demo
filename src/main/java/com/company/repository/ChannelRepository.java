package com.company.repository;

import com.company.dto.channel.ChannelDTO;
import com.company.entity.ChannelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;


public interface ChannelRepository extends PagingAndSortingRepository<ChannelEntity, String> {


    Optional<ChannelEntity> findByIdAndVisible(String id, boolean b);

    @Query("from ChannelEntity  ch where ch.visible=true ")
    Page<ChannelEntity> pagination(Pageable pageable);

    List<ChannelEntity> findByProfileIdAndVisible(Integer id, boolean b);
}
