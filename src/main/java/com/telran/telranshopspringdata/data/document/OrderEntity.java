package com.telran.telranshopspringdata.data.document;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    @Id
    private String id;
    private Timestamp date;
    private OrderStatus status;
    private UserEntity owner;
    private List<ProductOrderEntity> products;
}
