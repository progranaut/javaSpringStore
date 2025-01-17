package com.ivlev.javaspringstore.service;

import com.ivlev.javaspringstore.model.CategoryDto;
import com.ivlev.javaspringstore.model.ProductDto;
import com.ivlev.javaspringstore.entity.Category;
import com.ivlev.javaspringstore.entity.Product;
import com.ivlev.javaspringstore.mapper.ProductMapper;
import com.ivlev.javaspringstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CategoryService categoryService;

    public ProductDto addProduct (ProductDto productDto) {

        Product product = productMapper.toEntity(productDto);

        product.setCategory(categoryService.toEntity(productDto.getCategoryDto()));

        product = productRepository.save(product);

        productDto = productMapper.toDto(product);

        productDto.setCategoryDto(categoryService.toDto(product.getCategory()));

        return productDto;
    }

    public List<ProductDto> getAllProduct() {

        return productRepository.findAll().stream()
//TODO Для пользователей сделать отдельный запрос с фильтрацией
// возвращать только видимые и с количеством больше нуля
//                .filter(product -> product.isVisibility())
//                .filter(product -> product.getAvailability() > 0)
                .map(product -> {
                    ProductDto productDto = productMapper.toDto(product);
                    productDto.setCategoryDto(CategoryDto.builder()
                            .id(product.getCategory().getId())
                            .categoryType(product.getCategory().getCategoryType())
                            .build());
                    return productDto;
                })
                .collect(Collectors.toList());

    }

    public Product findProductById(UUID id) {

        return productRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("Продукт не найден!"));

    }

    public ProductDto toDto(Product product) {

        ProductDto productDto = productMapper.toDto(product);
        if (product.getCategory() != null) {
            productDto.setCategoryDto(categoryService.toDto(product.getCategory()));
        }

        return productDto;

    }

    public Product toEntity(ProductDto productDto) {

        Product product = productMapper.toEntity(productDto);
        if (productDto.getCategoryDto() != null) {
            product.setCategory(categoryService.toEntity(productDto.getCategoryDto()));
        }

        return product;

    }

    public ProductDto changeProduct(ProductDto productDto) {

        Product product = toEntity(productDto);

        product = productRepository.save(product);

        productDto = toDto(product);

        return productDto;
    }

    public List<Product> findProductsById(List<UUID> uuids) {

        return productRepository.findAllById(uuids);

    }

    public void changeProducts(List<Product> products) {

        productRepository.saveAll(products);

    }
}
