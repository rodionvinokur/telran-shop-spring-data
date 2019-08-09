package com.telran.telranshopspringdata.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductPriceUpdateDto {
    private String productId;
    private BigDecimal price;
}
