package com.telran.telranshopspringdata.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Builder
public class ProductResponseDto {
    private String id;
    private String name;
    private BigDecimal price;
    private String categoryId;
}
