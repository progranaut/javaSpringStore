package com.ivlev.javaspringstore.controller;

import com.ivlev.javaspringstore.model.*;
import com.ivlev.javaspringstore.service.StoreService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/add-in-basket/{id}")
    public ResponseEntity<?> addProductInBasket(@PathVariable UUID id){
        return storeService.addProductInBasket(id);
    }

    @PostMapping("/add-basket")
    public void addBasket(@RequestBody List<UserProductRelationDto> uprd){
        storeService.addBasket(uprd);
    }

    @GetMapping("/all-products-in-basket")
    public List<UserProductRelationDto> getProductsInCart () {
        return storeService.getAllProductsInBasket();
    }

    @GetMapping("/product-in-basket/{id}")
    public ResponseEntity<?> getProductInBasket(@PathVariable UUID id) {
        return storeService.getProductInBasket(id);
    }

    @DeleteMapping("/delete-product-from-basket/{id}")
    public ResponseEntity<?> deleteProductInBasket(@PathVariable UUID id) {
        return storeService.deleteProductFromBasket(id);
    }

    @GetMapping("/current-user-name-roll")
    public ResponseEntity<?> userName() {
        return storeService.getCurrentUserNameAndRole();
    }

    @GetMapping("/all-products")
    public List<ProductDto> allProducts() {
        return storeService.getAllProduct();
    }

    @GetMapping("/current-user")
    public UserDto getCurrentUser() {
        return storeService.getCurrentUserDto();
    }

    @GetMapping("/add-order")
    public ResponseEntity<?> addOrder() {
        return storeService.addOrder();
    }

    @GetMapping("/all-product-categories")
    public List<CategoryDto> getProductCategories(){
        return storeService.getAllProductCategories();
    }

}
