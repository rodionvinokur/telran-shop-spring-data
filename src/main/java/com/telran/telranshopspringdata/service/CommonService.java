package com.telran.telranshopspringdata.service;

import com.telran.telranshopspringdata.controller.dto.CategoryDto;
import com.telran.telranshopspringdata.controller.dto.ProductDto;

import java.util.List;

public interface CommonService {
    List<ProductDto> getAllProducts();
    List<CategoryDto> getAllCategories();
    List<ProductDto> getProductsByCategory(String categoryId);
}
