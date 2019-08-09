package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.document.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface UserRepository extends CrudRepository<UserEntity, String> {
    default UserEntity getUserByEmail(String userEmail) {
        UserEntity userEntity = findById(userEmail).orElse(null);
        if (userEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with email = " + userEmail + " not found");
        }
        return userEntity;
    }
}
