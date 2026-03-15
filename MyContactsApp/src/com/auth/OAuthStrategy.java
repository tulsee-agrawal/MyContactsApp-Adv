package com.auth;

import com.model.User;
import com.service.UserService;

import java.util.Optional;

public class OAuthStrategy implements AuthenticationStrategy {

    private final UserService userService;

    public OAuthStrategy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<User> authenticate(String email, String oauthToken) {
        if (email == null || oauthToken == null) return Optional.empty();
        if (!"OK".equals(oauthToken.trim())) return Optional.empty();

        User user = userService.findByEmail(email.trim().toLowerCase());
        return Optional.ofNullable(user);
    }
}