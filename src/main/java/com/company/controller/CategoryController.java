package com.company.controller;

import com.company.dto.category.CategoryCreateDTO;
import com.company.dto.category.CategoryDTO;
import com.company.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    // SECURE
    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody @Valid CategoryCreateDTO dto){
        categoryService.create(dto);
        return ResponseEntity.ok().body("SuccsessFully created");
    }

    @GetMapping("/adm/list")
    public ResponseEntity<?> getlist( ) {
        List<CategoryDTO> list = categoryService.getListOnlyForAdmin();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/adm/update")
    private ResponseEntity<?> update(@RequestBody  @Valid CategoryDTO dto){
        categoryService.update(dto);
        return ResponseEntity.ok().body("Succsessfully updated");
    }

    @DeleteMapping("/adm/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return ResponseEntity.ok().body("Successfully deleted");
    }




}
