package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.document.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

public interface ProductRepository extends CrudRepository<ProductEntity, String > {
    Stream<ProductEntity> findAllByCategory_Id(String category);
    ProductEntity findByName(String productName);

   default ProductEntity getProductById(String productId) {
        ProductEntity productEntity = findById(productId).orElse(null);
        if (productEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id = " + productId + " not found");
        }
        return productEntity;
    }
}

