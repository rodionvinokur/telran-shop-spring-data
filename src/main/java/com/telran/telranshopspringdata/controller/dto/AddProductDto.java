package com.telran.telranshopspringdata.controller.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AddProductDto {
    private String productId;
    private int count;
}
