package com.company.repository;

import com.company.entity.PlaylistVideoEntity;
import com.company.entity.VideoTagEntity;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PlaylistVideoRepository extends PagingAndSortingRepository<PlaylistVideoEntity, Integer> {

}
