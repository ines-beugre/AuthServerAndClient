package com.auth.demo.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/me")
public class MeController {

    @GetMapping(produces = "application/json")
    public String getMe(TokenAuthentication token) {
        return token.asJson();
    }
}
