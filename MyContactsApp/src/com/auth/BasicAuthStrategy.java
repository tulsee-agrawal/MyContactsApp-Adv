package com.auth;

import com.model.User;
import com.service.UserService;
import com.util.PasswordHasher;

import java.util.Optional;

public class BasicAuthStrategy implements AuthenticationStrategy {

    private final UserService userService;

    public BasicAuthStrategy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<User> authenticate(String email, String rawPassword) {
        if (email == null || rawPassword == null) return Optional.empty();

        User user = userService.findByEmail(email.trim().toLowerCase());
        if (user == null) return Optional.empty();

        String candidateHash = PasswordHasher.hash(rawPassword);
        if (candidateHash.equals(user.getPasswordHash())) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}