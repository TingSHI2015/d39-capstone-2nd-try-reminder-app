package com.github.tingshi2015.backend.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping()
public class AuthController {

    @GetMapping("/api/auth/me")
    public String getMe(@AuthenticationPrincipal OAuth2User user) {
//        return user.getAttributes().get("login").toString();
        return user != null ? user.getAttributes().get("login").toString() : null;
    }


}