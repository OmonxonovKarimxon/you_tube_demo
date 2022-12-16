package com.company.service;


import com.company.dto.category.CategoryCreateDTO;
import com.company.dto.category.CategoryDTO;
import com.company.entity.CategoryEntity;
import com.company.entity.ProfileEntity;
import com.company.exps.BadRequestException;
import com.company.repository.CategoryRepository;
import com.company.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void create(CategoryCreateDTO dto) {
        ProfileEntity profileEntity = currentUser();

        CategoryEntity entity = new CategoryEntity();
       entity.setName(dto.getName());
       entity.setAdminId(profileEntity.getId());
       categoryRepository.save(entity);
    }
    public void update(CategoryDTO dto) {
        ProfileEntity profileEntity = currentUser();

        Optional<CategoryEntity> optional = categoryRepository.findById(dto.getId());
        if(optional.isEmpty()){
            throw  new BadRequestException("we have not this  category");
        }
       categoryRepository.updateName(dto.getName(),profileEntity.getId());

    }

    public void delete(Integer id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if(optional.isEmpty()){
            throw  new BadRequestException("we have not this  category");
        }
        categoryRepository.deleteById(id);
    }


    public List<CategoryDTO> getListOnlyForAdmin() {
        List<CategoryEntity> allByVisible = categoryRepository.findAllByVisible(true);
        List<CategoryDTO> dtoList  = new ArrayList<>();
        allByVisible.forEach(entity -> {
            dtoList.add(entityToDTO(entity));
        });
        return dtoList;
    }

private CategoryDTO entityToDTO(CategoryEntity entity){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAdminId(entity.getAdminId());
        return dto;
}
    public ProfileEntity currentUser() {
        return SpringUtil.currentUser().getProfile();
    }

}
