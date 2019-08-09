package com.telran.telranshopspringdata.controller;


import com.telran.telranshopspringdata.controller.dto.*;
import com.telran.telranshopspringdata.service.Mapper;
import com.telran.telranshopspringdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("user")
public class UserAdvanceFuncController {
    @Autowired
    UserService service;

    @PostMapping("cart")
    public ShoppingCartDto addProductToCart(Principal principal,
                                            @RequestBody AddProductDto dto) {
        return service.addProductToCart(principal.getName(), dto.getProductId(), dto.getCount())
                .orElseThrow();
    }

    @GetMapping("cart")
    public ShoppingCartDto getShoppingCart(Principal principal) {
        return service.getShoppingCart(principal.getName()).get();
    }

    @DeleteMapping("cart/{productId}/{count}")
    public ShoppingCartDto removeProductFromCart(@PathVariable("productId") String productId, Principal principal,
                                                 @PathVariable("count") int count) {
        return service.removeProductFromCart(principal.getName(), productId, count).get();
    }

    @DeleteMapping("cart/all")
    public void clearShoppingCart(Principal principal) {
        service.clearShoppingCart(principal.getName());
    }

    @GetMapping("orders")
    public List<OrderDto> getAllOrdersByEmail(Principal principal){
        return service.getOrders(principal.getName());
    }


    @GetMapping("checkout")
    public OrderDto checkout(Principal principal) {
        return service.checkout(principal.getName()).get();
    }

}
