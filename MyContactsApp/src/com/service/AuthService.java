package com.service;

import com.auth.AuthenticationStrategy;
import com.model.User;
import com.session.SessionManager;

import java.util.Optional;

public class AuthService {

    private AuthenticationStrategy strategy;

    public AuthService(AuthenticationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(AuthenticationStrategy strategy) {
        this.strategy = strategy;
    }

    public Optional<String> login(String identifier, String secret) {
        if (strategy == null) return Optional.empty();

        Optional<User> userOpt = strategy.authenticate(identifier, secret);
        if (userOpt.isEmpty()) return Optional.empty();

        String userId = userOpt.get().getId();
        String sessionId = SessionManager.getInstance().createSession(userId);
        return Optional.of(sessionId);
    }

    public void logout(String sessionId) {
        SessionManager.getInstance().invalidate(sessionId);
    }
}