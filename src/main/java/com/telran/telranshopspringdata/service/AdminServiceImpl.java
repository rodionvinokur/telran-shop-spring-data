package com.telran.telranshopspringdata.service;

import com.telran.telranshopspringdata.app.additional.Maximizer;
import com.telran.telranshopspringdata.controller.dto.*;
import com.telran.telranshopspringdata.data.*;
import com.telran.telranshopspringdata.data.entity.CategoryEntity;
import com.telran.telranshopspringdata.data.entity.ProductEntity;
import com.telran.telranshopspringdata.data.entity.ProductOrderEntity;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@Transactional(isolation = READ_COMMITTED)
public class AdminServiceImpl implements AdminService {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private OrderRepository orderRepository;

    public AdminServiceImpl(UserRepository userRepository,
                            ProductRepository productRepository,
                            CategoryRepository categoryRepository,
                            OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public String addCategory(String name) {
        if (categoryRepository.findByName(name) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category with name: " + name + "already exist");
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        categoryEntity.setProducts(new ArrayList<>());
        return categoryRepository.save(categoryEntity).getId();
    }

    @Override
    public String addProduct(String productName, BigDecimal price, String categoryId) {
        Optional<CategoryEntity> categoryEntityOpt = categoryRepository.getCategory(categoryId);
        if (productRepository.findByName(productName) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product with name: " + productName + "already exist");
        }
        ProductEntity productEntity = ProductEntity.builder()
                .category(categoryEntityOpt.get())
                .name(productName)
                .price(price)
                .build();
        productRepository.save(productEntity);
        categoryEntityOpt.get().getProducts().add(productEntity);
        return productEntity.getId();
    }

    @Override
    public boolean removeProduct(String productId) {
        ProductEntity productEntity = productRepository.getProductById(productId);
        productRepository.delete(productEntity);
        return true;
    }

    @Override
    public boolean changeProductPrice(String productId, BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) >= 0) {
            productRepository.getProductById(productId).setPrice(price);
            return true;
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Price < 0");
    }

    @Override
    public List<ProductStatisticDto> getMostPopularProduct() {
        return orderRepository.getProductPopularStatistics().map(Mapper::map).collect(Collectors.toList());
    }

    @Override
    public List<ProductStatisticDto> getMostProfitableProduct() {
        return orderRepository.getProductProfitStatistics().map(Mapper::map).collect(Collectors.toList());
    }

    @Override
    public List<UserStatisticDto> getMostActiveUser() {
        return orderRepository.getUserActiveStatistics().map(this::map).collect(Collectors.toList());
    }

    @Override
    public List<UserStatisticDto> getMostProfitableUser() {
        return orderRepository.getUserProfitStatistics().map(this::map).collect(Collectors.toList());
    }

    private UserStatisticDto map(UserStatisticDtoProjection pro) {
        List<ProductOrderDto> dtoList = orderRepository.getProductsOrderListByUserEmail(pro.getUserEmail()).stream().map(Mapper::map).collect(Collectors.toList());
        return new UserStatisticDto(pro.getUserEmail(), dtoList, pro.getTotalProductsCount(), pro.getTotalAmount());
    }

}
