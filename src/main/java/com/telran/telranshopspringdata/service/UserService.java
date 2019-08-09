package com.telran.telranshopspringdata.service;

import com.telran.telranshopspringdata.controller.dto.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> addUserInfo(String email, String name, String phone);
    Optional<UserDto> getUserInfo(String email);
    Optional<ShoppingCartDto> addProductToCart(String userEmail, String productId, int count);
    Optional<ShoppingCartDto> removeProductFromCart(String userEmail,String productId,int count);
    Optional<ShoppingCartDto> getShoppingCart(String userEmail);
    boolean clearShoppingCart(String userEmail);
    boolean addBalance(String userEmail, BigDecimal price);
    List<OrderDto> getOrders(String userEmail);
    Optional<OrderDto> checkout(String userEmail);
}
