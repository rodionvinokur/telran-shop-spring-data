package com.telran.telranshopspringdata.controller.dto;

import java.math.BigDecimal;

public interface UserStatisticDtoProjection {
     String getUserEmail();
     Long getTotalProductsCount();
     BigDecimal getTotalAmount();
}
