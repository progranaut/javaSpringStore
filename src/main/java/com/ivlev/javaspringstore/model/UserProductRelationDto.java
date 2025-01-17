package com.ivlev.javaspringstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProductRelationDto {

    private ProductDto productDto;

    private int quantity;

}
