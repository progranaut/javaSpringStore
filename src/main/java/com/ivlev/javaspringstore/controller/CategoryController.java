package com.ivlev.javaspringstore.controller;

import com.ivlev.javaspringstore.model.CategoryDto;
import com.ivlev.javaspringstore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public void addCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.addCategory(categoryDto);
    }

    @GetMapping("/all-product-categories")
    public List<CategoryDto> getProductCategories(){
        return categoryService.getAllProductCategories();
    }

}
