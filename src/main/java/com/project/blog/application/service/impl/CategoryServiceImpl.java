package com.project.blog.application.service.impl;

import com.project.blog.application.entity.Category;
import com.project.blog.application.exceptions.ResourceNotFoundException;
import com.project.blog.application.payloads.CategoryDto;
import com.project.blog.application.repository.CategoryRepo;
import com.project.blog.application.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=this.dtoToCategory(categoryDto);
        Category saved= categoryRepo.save(category);

        return this.categoryToDto(saved);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer category_id) {
        Category category= categoryRepo.findById(category_id).orElse(null);
        if(category==null){
            throw new ResourceNotFoundException("Category", "id", category_id);
        }
        category.setCategory_title(categoryDto.getCategory_title());
        category.setCategory_description(categoryDto.getCategory_description());
        categoryRepo.save(category);

        return this.categoryToDto(category);
    }

    @Override
    public CategoryDto getCategory(Integer category_id) {
        Category category= categoryRepo.findById(category_id).orElse(null);
        if (category==null){
            throw new ResourceNotFoundException("Category", "id", category_id);
        }


        return this.categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories= categoryRepo.findAll();
        List<CategoryDto> listOfCategories=categories.stream().map(category -> categoryToDto(category)).collect(Collectors.toList());

        return listOfCategories;
    }

    @Override
    public void delete(Integer category_id) {
Category category= categoryRepo.findById(category_id).orElse(null);
if(category==null){
    throw new ResourceNotFoundException("Category", "id", category_id);
}
categoryRepo.delete(category);

    }

    public CategoryDto categoryToDto(Category category){
        CategoryDto categoryDto= modelMapper.map(category, CategoryDto.class);
        return  categoryDto;
    }

    public Category dtoToCategory(CategoryDto categoryDto){
        Category category= modelMapper.map(categoryDto, Category.class);
        return  category;
    }
}
