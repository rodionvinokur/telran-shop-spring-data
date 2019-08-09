package com.telran.telranshopspringdata.controller;

import com.telran.telranshopspringdata.controller.dto.AddBalanceDto;
import com.telran.telranshopspringdata.controller.dto.UserDto;
import com.telran.telranshopspringdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("user")
public class UserSimpleFuncController {
    @Autowired
    UserService service;

    @PostMapping("balance")
    public boolean increaseUserBalance(@RequestBody AddBalanceDto dto, Principal principal) {
        return service.addBalance(principal.getName(), dto.getAmount());
    }

    @PostMapping
    public UserDto addUserInfo(@RequestBody UserDto user, Principal principal) {
        return service.addUserInfo(principal.getName(), user.getName(), user.getPhone()).get();
    }

    @GetMapping
    public UserDto getUserInfo(Principal principal) {
        return service.getUserInfo(principal.getName()).get();
    }

}
