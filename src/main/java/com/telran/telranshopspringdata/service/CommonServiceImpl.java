package com.telran.telranshopspringdata.service;

import com.telran.telranshopspringdata.controller.dto.CategoryDto;
import com.telran.telranshopspringdata.controller.dto.ProductDto;
import com.telran.telranshopspringdata.data.CategoryRepository;
import com.telran.telranshopspringdata.data.ProductOrderRepository;
import com.telran.telranshopspringdata.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {
    public CommonServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(Mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .map(Mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategory(String categoryId) {
        return categoryRepository.getCategory(categoryId).get().getProducts().stream()
                .map(Mapper::map)
                .collect(Collectors.toList());
    }
}
