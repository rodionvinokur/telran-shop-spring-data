package com.telran.telranshopspringdata.data.document;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Builder

public class UserDetailsEntity {
    @Id
    private String email;
    private String password;
    private List<UserRoleEntity> roles;
    private UserEntity profile;
}
