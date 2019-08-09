package com.telran.telranshopspringdata.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "user_details")
public class UserDetailsEntity {
    @Id
    private String email;
    private String password;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
            name = "user_email", referencedColumnName = "email"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_role", referencedColumnName = "role"
    )
    )
    private List<UserRoleEntity> roles;
    @OneToOne
    @JoinColumn(name = "email")
    private UserEntity profile;
}
