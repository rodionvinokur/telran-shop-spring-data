package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.document.UserRoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRoleEntity, String> {
}
