package com.ivlev.javaspringstore.mapper;

import com.ivlev.javaspringstore.entity.Product;
import com.ivlev.javaspringstore.model.ProductDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId().toString())
                .serialNumber(product.getSerialNumber())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .availability(product.getAvailability())
                .visibility(product.isVisibility())
                .build();
    }

    public Product toEntity(ProductDto productDto) {

        Product product = new Product();

        if (productDto.getId() != null) {
            product.setId(UUID.fromString(productDto.getId()));
        }

        if (productDto.getSerialNumber() != null) {
            product.setSerialNumber(productDto.getSerialNumber());
        }

        if (productDto.getName() != null) {
            product.setName(productDto.getName());
        }

        if (productDto.getPrice() != null) {
            product.setPrice(productDto.getPrice());
        }

        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }

        if (productDto.getImageUrl() != null) {
            product.setImageUrl(productDto.getImageUrl());
        }

        product.setAvailability(productDto.getAvailability());

        product.setVisibility(productDto.isVisibility());

        return product;

    }

}