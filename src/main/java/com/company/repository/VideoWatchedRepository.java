package com.company.repository;

import com.company.entity.VideoWatchedEntity;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface VideoWatchedRepository extends PagingAndSortingRepository<VideoWatchedEntity, Integer> {

}
