package com.auth;

import com.model.User;

import java.util.Optional;

public interface AuthenticationStrategy {
    Optional<User> authenticate(String identifier, String secret);
}