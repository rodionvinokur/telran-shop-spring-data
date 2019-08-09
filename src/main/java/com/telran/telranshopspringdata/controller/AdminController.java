package com.telran.telranshopspringdata.controller;

import com.telran.telranshopspringdata.controller.dto.*;
import com.telran.telranshopspringdata.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping("category")
    public String addCategory(@RequestBody CategoryDto dto) {
        return service.addCategory(dto.getName());
    }

    @PostMapping("product")
    public String addProduct(@RequestBody ProductDto dto) {
        return service.addProduct(dto.getName(), dto.getPrice(), dto.getCategory().getId());
    }

    @DeleteMapping("product/{pid}")
    public boolean deleteProduct(@PathVariable String pid) {
        return service.removeProduct(pid);
    }

    @PutMapping("product/price")
    public boolean updateProduct(@RequestBody ProductPriceUpdateDto dto) {
        return service.changeProductPrice(dto.getProductId(), dto.getPrice());
    }

    @GetMapping("product/popular")
    public List<ProductStatisticDto> getMostPopularProduct() {
        return service.getMostPopularProduct();
    }

    @GetMapping("product/profit")
    public List<ProductStatisticDto> getMostProfitableProduct() {
        return service.getMostProfitableProduct();
    }

    @GetMapping("user/active")
    public List<UserStatisticDto> getMostActiveUser() {
        return service.getMostActiveUser();
    }

    @GetMapping("user/profit")
    public List<UserStatisticDto> getMostProfitableUser() {
        return service.getMostProfitableUser();
    }
}