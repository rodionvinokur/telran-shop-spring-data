package com.telran.telranshopspringdata.data.document;


import lombok.*;
import org.springframework.data.annotation.Id;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Builder

public class UserRoleEntity {
    @Id
    private String role;
    private List<UserDetailsEntity> users;
}
