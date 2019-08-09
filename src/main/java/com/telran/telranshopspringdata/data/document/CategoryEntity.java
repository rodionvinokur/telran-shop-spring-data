package com.telran.telranshopspringdata.data.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@ToString(exclude = "products")

public class CategoryEntity {
    @Id
    private String id;
    private String name;
    private List<ProductEntity> products;
}
