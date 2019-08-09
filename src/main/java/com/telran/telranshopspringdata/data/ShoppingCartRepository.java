package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.document.ShoppingCartEntity;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository <ShoppingCartEntity, String> {
    ShoppingCartEntity findByOwner_Email(String email);

}
