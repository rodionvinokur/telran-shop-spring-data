package com.telran.telranshopspringdata.controller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {
    private String email;
    private String password;
}
