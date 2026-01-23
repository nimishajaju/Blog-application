package com.project.blog.application.service;

import com.project.blog.application.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //CREATE
    CategoryDto createCategory(CategoryDto categoryDto);


    //UPDATE
    CategoryDto updateCategory(CategoryDto categoryDto,Integer category_id);

    //getbyId
    CategoryDto getCategory(Integer category_id);

    //getAll
    List<CategoryDto>  getAllCategory();

    //Delete
    void delete(Integer category_id);



}
