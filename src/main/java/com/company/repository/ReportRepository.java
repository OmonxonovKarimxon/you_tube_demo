package com.company.repository;

import com.company.entity.ReportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ReportRepository extends PagingAndSortingRepository<ReportEntity, Integer> {

    @Query("from ReportEntity ")
    Page<ReportEntity> pagination(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE  ReportEntity  set visible=false  where id=:reportId")
     void delete(Integer reportId);


    List<ReportEntity> findByProfileIdAndVisible(Integer profileId, Boolean visible);
}



