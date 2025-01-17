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
public class OrderProductRelationDto {

    private ProductDto productDto;

    private int relation;

    private Double relationPrice;

}
