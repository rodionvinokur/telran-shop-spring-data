package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.document.UserDetailsEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailsRepository extends CrudRepository<UserDetailsEntity, String> {
}
