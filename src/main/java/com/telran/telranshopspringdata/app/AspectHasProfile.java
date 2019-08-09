package com.telran.telranshopspringdata.app;

import com.telran.telranshopspringdata.data.UserRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
public class AspectHasProfile {
    private UserRepository userRepository;

    public AspectHasProfile(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Before("execution(* com.telran.telranshopspringdata.controller.UserAdvanceFuncController.*(..))")
    public void doCheckThatUserHasProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!userRepository.existsById(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Please, fill profile before");
        }
    }
}
