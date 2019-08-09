package com.telran.telranshopspringdata.data.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private CategoryEntity category;
}
