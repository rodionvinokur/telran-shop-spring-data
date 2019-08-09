package com.telran.telranshopspringdata.service;

import com.telran.telranshopspringdata.data.UserDetailsRepository;
import com.telran.telranshopspringdata.data.entity.UserDetailsEntity;
import com.telran.telranshopspringdata.data.entity.UserRoleEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserDetailsRepository repository;

    public CustomUserDetailService(UserDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetailsEntity entity = repository.findById(s).orElse(null);
        if (entity == null) {
            throw new UsernameNotFoundException("");
        }
        String[] roles = entity.getRoles()
                .stream()
                .map(UserRoleEntity::getRole)
                .toArray(String[]::new);
        return new User(entity.getEmail(), entity.getPassword(), AuthorityUtils.createAuthorityList(roles));
    }
}
