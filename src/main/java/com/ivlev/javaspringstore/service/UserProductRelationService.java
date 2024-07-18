package com.ivlev.javaspringstore.service;

import com.ivlev.javaspringstore.model.UserProductRelationDto;
import com.ivlev.javaspringstore.entity.Product;
import com.ivlev.javaspringstore.entity.User;
import com.ivlev.javaspringstore.entity.UserProductRelation;
import com.ivlev.javaspringstore.repository.UserProductRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProductRelationService {

    private final UserProductRelationRepository userProductRelationRepository;

    private final ProductService productService;

    public ResponseEntity<?> addUserProductRelation(User user, Product product) {

        UserProductRelation userProductRelation;

        try {

            userProductRelation = getRelation(user.getId(), product.getId());

            if (userProductRelation.getProduct().getAvailability() <= userProductRelation.getQuantity()) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            int quantity = userProductRelation.getQuantity();
            userProductRelation.setQuantity(++quantity);

        } catch (Exception e) {

            userProductRelation = UserProductRelation.builder()
                    .user(user)
                    .product(product)
                    .quantity(1)
                    .build();

        }

        userProductRelationRepository.save(userProductRelation);

        userProductRelation = getRelation(user.getId(), product.getId());

        return new ResponseEntity<>(UserProductRelationDto.builder()
                .productDto(productService.toDto(product))
                .quantity(userProductRelation.getQuantity())
                .build(), HttpStatus.OK);
    }

    public UserProductRelation getRelation(UUID userId, UUID productId) {

        return userProductRelationRepository.findRelationByIds(userId, productId)
                .orElseThrow();

    }

    public List<UserProductRelation> getRelations(User user) {

        return userProductRelationRepository.findAllByUserId(user.getId());

    }

    public ResponseEntity<?> delUserProductRelation(User user, Product product) {

        UserProductRelation userProductRelation;

        try {
            userProductRelation = getRelation(user.getId(), product.getId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (userProductRelation.getQuantity() > 1) {

            int quantity = userProductRelation.getQuantity();
            userProductRelation.setQuantity(--quantity);
            userProductRelationRepository.save(userProductRelation);

        } else {

            userProductRelationRepository.delete(userProductRelation);

        }

        try {
            userProductRelation = getRelation(user.getId(), product.getId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(UserProductRelationDto.builder()
                .productDto(productService.toDto(product))
                .quantity(userProductRelation.getQuantity())
                .build(), HttpStatus.OK);
    }

    public void delAllUserProductRelation(List<UserProductRelation> upr) {

        userProductRelationRepository.deleteAll(upr);

    }

    public void addAllRelation(List<UserProductRelation> userProductRelations) {

        userProductRelationRepository.saveAll(userProductRelations);

    }
}
