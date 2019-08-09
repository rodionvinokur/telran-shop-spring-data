package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.controller.dto.ProductStatisticDtoProjection;
import com.telran.telranshopspringdata.controller.dto.UserStatisticDtoProjection;
import com.telran.telranshopspringdata.data.entity.OrderEntity;
import com.telran.telranshopspringdata.data.entity.ProductOrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Stream;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {
    @Query(value = "select p.name as productName, c.name as productCategory, sum(p.count) as numberOfPurchases, sum(p.price * p.count) as totalAmount " +
            "from orders o join product_order p on o.id=p.order_id  join categories c on p.category_id=c.id " +
            "group by p.name , c.name, p.product_id " +
            "having  numberOfPurchases = (select sum(count) from product_order group by  product_id order by 1 desc limit 1)",
            nativeQuery = true)
    Stream<ProductStatisticDtoProjection> getProductPopularStatistics();

    @Query(value = "select p.name as productName, c.name as productCategory, sum(p.count) as numberOfPurchases, sum(p.price * p.count) as totalAmount " +
            "from orders o join product_order p on o.id=p.order_id  join categories c on p.category_id=c.id " +
            "group by p.name , c.name, p.product_id " +
            "having  totalAmount = (select sum(price * count) from product_order group by  product_id order by 1 desc limit 1)",
            nativeQuery = true)
    Stream<ProductStatisticDtoProjection> getProductProfitStatistics();

    @Query(value = "select u.email as userEmail, sum(p.count) as totalProductsCount, sum(CAST((p.price * p.count) AS DECIMAL(19,2))) as totalAmount " +
            "from orders o join product_order p on o.id=p.order_id join users u on u.email = o.owner_id " +
            "group by u.email " +
            "having totalProductsCount = (select sum(p1.count) from orders o1 join product_order p1 on o1.id=p1.order_id join users u1 on u1.email = o1.owner_id group by u1.email)",
            nativeQuery = true)
    Stream<UserStatisticDtoProjection> getUserActiveStatistics();

    @Query(value = "select u.email as userEmail, sum(p.count) as totalProductsCount, sum(CAST((p.price * p.count) AS DECIMAL(19,2))) as totalAmount " +
            "from orders o join product_order p on o.id=p.order_id join users u on u.email = o.owner_id " +
            "group by u.email " +
            "having totalAmount = (select sum(CAST((p1.price * p1.count) AS DECIMAL(19,2))) from orders o1 join product_order p1 on o1.id=p1.order_id join users u1 on u1.email = o1.owner_id group by u1.email)",
            nativeQuery = true)
    Stream<UserStatisticDtoProjection> getUserProfitStatistics();

    @Query(value = "select poe from OrderEntity o join o.products poe join o.owner w WHERE w.email = :email")
    List<ProductOrderEntity> getProductsOrderListByUserEmail(String email);
}
