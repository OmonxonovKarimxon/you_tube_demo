package com.company.repository;

import com.company.entity.VideoEntity;
import com.company.entity.VideoTagEntity;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface VideoTagRepository extends PagingAndSortingRepository<VideoTagEntity, Integer> {

}
