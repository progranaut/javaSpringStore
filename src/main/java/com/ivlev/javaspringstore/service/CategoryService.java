package com.ivlev.javaspringstore.service;

import com.ivlev.javaspringstore.model.CategoryDto;
import com.ivlev.javaspringstore.entity.Category;
import com.ivlev.javaspringstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void addCategory(CategoryDto categoryDto) {

        Category category = Category.builder()
                .categoryType(categoryDto.getCategoryType())
                .build();

        categoryRepository.save(category);

    }

    public List<CategoryDto> getAllProductCategories() {

        return categoryRepository.findAll().stream()
                .map(category -> CategoryDto.builder()
                        .id(category.getId())
                        .categoryType(category.getCategoryType())
                        .build())
                .collect(Collectors.toList());

    }

    public Category getCategoryByCategoryType(String categoryType) {

        return categoryRepository.findAll().stream()
                .filter(category -> category
                        .getCategoryType()
                        .equals(categoryType))
                .findFirst()
                .orElseThrow();

    }

    public Category toEntity(CategoryDto categoryDto) {

        if (categoryDto == null) {
            return null;
        }

        Category category;

        if (categoryDto.getId() == null || categoryDto.getId().equals("")) {
            category = getCategoryByCategoryType(categoryDto.getCategoryType());
        } else {
            category = Category.builder()
                    .id(categoryDto.getId())
                    .build();
        }

        return category;
    }

    public CategoryDto toDto(Category category) {

        if (category == null) {
            return  null;
        }

        return CategoryDto.builder()
                .id(category.getId())
                .categoryType(category.getCategoryType())
                .build();

    }
}