package com.ivlev.javaspringstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private String id;

    private String serialNumber;

    private String name;

    private Double price;

    private CategoryDto categoryDto;

    private String description;

    private String imageUrl;

    private int availability;

    private boolean visibility;

}
