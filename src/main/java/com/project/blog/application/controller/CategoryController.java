package com.project.blog.application.controller;

import com.project.blog.application.payloads.CategoryDto;
import com.project.blog.application.payloads.ResponseApi;
import com.project.blog.application.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto>  createCategory(@Valid @RequestBody CategoryDto categoryDto){
       CategoryDto category=categoryService.createCategory(categoryDto);
       return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{category_id}")
    public  ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer category_id){
       CategoryDto update=  categoryService.updateCategory(categoryDto,category_id);

        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categories= categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer category_id){
        CategoryDto category1= categoryService.getCategory(category_id);
        return  new ResponseEntity<>(category1, HttpStatus.OK);
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<ResponseApi> deleteCategroy(@PathVariable Integer category_id){
        categoryService.delete(category_id);
        return  new ResponseEntity<>(new ResponseApi("category deleted", true), HttpStatus.OK);
    }

}
