package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, String> {
    CategoryEntity findByName(String name);

    default Optional<CategoryEntity> getCategory(String categoryId) {
        Optional<CategoryEntity> categoryEntityOpt;
        if ((categoryEntityOpt = findById(categoryId)).equals(Optional.empty())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id: " + categoryId + "not found");
        }
        return categoryEntityOpt;
    }
}
