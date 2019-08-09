package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.entity.ShoppingCartEntity;
import com.telran.telranshopspringdata.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository <ShoppingCartEntity, String> {
    //@Query("SELECT s FROM ShoppingCartEntity s WHERE s.owner = :userEntity")
    ShoppingCartEntity findByOwner_Email(String email);
    //ShoppingCartEntity findByOwner(UserEntity entity);
}
