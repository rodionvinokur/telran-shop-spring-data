package com.telran.telranshopspringdata.controller.dto;

import java.math.BigDecimal;

public interface ProductStatisticDtoProjection {
     String getProductName();
     String getProductCategory();
     Long getNumberOfPurchases();
     BigDecimal getTotalAmount();
}
