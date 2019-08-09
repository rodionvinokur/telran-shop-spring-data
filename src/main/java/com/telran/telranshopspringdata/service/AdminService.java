package com.telran.telranshopspringdata.service;

import com.telran.telranshopspringdata.controller.dto.ProductStatisticDto;
import com.telran.telranshopspringdata.controller.dto.UserStatisticDto;

import java.math.BigDecimal;
import java.util.List;

public interface AdminService {
    String addCategory(String name);

    String addProduct(String productName, BigDecimal price, String categoryId);

    boolean removeProduct(String productId);

    boolean changeProductPrice(String productId, BigDecimal price);
    public List<ProductStatisticDto> getMostPopularProduct();
    public List<ProductStatisticDto> getMostProfitableProduct();
    public List<UserStatisticDto> getMostActiveUser();
    public List<UserStatisticDto> getMostProfitableUser();
}
