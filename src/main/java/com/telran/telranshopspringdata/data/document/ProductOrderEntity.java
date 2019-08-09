package com.telran.telranshopspringdata.data.document;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductOrderEntity {
    @Id
    private String id;
    private String productId;
    private String name;
    private int count;
    private BigDecimal price;
    private CategoryEntity category;
    private ShoppingCartEntity shoppingCart;
    private OrderEntity order;
}
