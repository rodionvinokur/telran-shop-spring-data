package com.telran.telranshopspringdata.service;

import com.telran.telranshopspringdata.controller.dto.AuthDto;
import com.telran.telranshopspringdata.data.UserDetailsRepository;
import com.telran.telranshopspringdata.data.entity.UserDetailsEntity;
import com.telran.telranshopspringdata.data.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserDetailsRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void registration(AuthDto dto) {
        if (repository.existsById(dto.getEmail())) {
            throw new RuntimeException("User already exist");
        }
        UserDetailsEntity entity = new UserDetailsEntity();
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setRoles(
                List.of(UserRoleEntity.builder()
                .role("ROLE_USER")
                .build())
        );
        repository.save(entity);
    }
}
