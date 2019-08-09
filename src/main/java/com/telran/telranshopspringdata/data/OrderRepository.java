package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.controller.dto.ProductStatisticDtoProjection;
import com.telran.telranshopspringdata.controller.dto.UserStatisticDtoProjection;
import com.telran.telranshopspringdata.data.document.OrderEntity;
import com.telran.telranshopspringdata.data.document.ProductOrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Stream;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {

    Stream<ProductStatisticDtoProjection> getProductPopularStatistics();

    Stream<ProductStatisticDtoProjection> getProductProfitStatistics();

    Stream<UserStatisticDtoProjection> getUserActiveStatistics();

    Stream<UserStatisticDtoProjection> getUserProfitStatistics();

   List<ProductOrderEntity> getProductsOrderListByUserEmail(String email);
}
