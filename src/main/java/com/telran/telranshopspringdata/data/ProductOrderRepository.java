package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.document.OrderEntity;
import com.telran.telranshopspringdata.data.document.ProductOrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductOrderRepository extends CrudRepository<ProductOrderEntity, String> {
    int deleteProductOrderEntitiesByShoppingCart(List<String> ids);
    int updateProductOrderEntitiesApplyCheckout(List<String> ids, OrderEntity orderEntity);

}
