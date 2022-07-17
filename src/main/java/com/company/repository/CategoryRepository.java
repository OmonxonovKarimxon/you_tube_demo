package com.company.repository;

import com.company.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Integer> {


    List<CategoryEntity> findAllByVisible(Boolean b);

    @Query("update CategoryEntity  set name=:name, adminId=:adminId where visible=:true")
    void updateName(String name,Integer adminId);

}
