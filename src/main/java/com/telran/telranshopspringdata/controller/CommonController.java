package com.telran.telranshopspringdata.controller;

import com.telran.telranshopspringdata.controller.dto.CategoryDto;
import com.telran.telranshopspringdata.controller.dto.ProductDto;
import com.telran.telranshopspringdata.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommonController {
    @Autowired
    private CommonService service;

    @GetMapping("products")
    public List<ProductDto> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("categories")
    public List<CategoryDto> getAllCategories() {
        return service.getAllCategories();
    }

    @GetMapping("products/{categoryName}")
    public List<ProductDto> getProductByCategory(@PathVariable("categoryName") String categoryName) {
        return service.getProductsByCategory(categoryName);
    }

}
