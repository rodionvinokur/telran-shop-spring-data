package com.telran.telranshopspringdata.data.entity;


import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "roles")
public class UserRoleEntity {
    @Id
    private String role;
    @ManyToMany(mappedBy = "roles")
    private List<UserDetailsEntity> users;
}
