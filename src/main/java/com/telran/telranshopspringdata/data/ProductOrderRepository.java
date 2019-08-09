package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.entity.OrderEntity;
import com.telran.telranshopspringdata.data.entity.ProductEntity;
import com.telran.telranshopspringdata.data.entity.ProductOrderEntity;
import com.telran.telranshopspringdata.data.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductOrderRepository extends CrudRepository<ProductOrderEntity, String> {
    @Query(value = "DELETE FROM ProductOrderEntity poe WHERE poe.id in :ids")
    @Modifying
    int deleteProductOrderEntitiesByShoppingCart(List<String> ids);
    @Modifying
    @Query(value = "UPDATE ProductOrderEntity poe SET poe.order = :orderEntity, poe.shoppingCart = null WHERE poe.id in :ids")
    int updateProductOrderEntitiesApplyCheckout(List<String> ids, OrderEntity orderEntity);

}
