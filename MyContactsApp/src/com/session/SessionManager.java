package com.session;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public final class SessionManager {
    private static final SessionManager INSTANCE = new SessionManager();

    private final Map<String, String> sessions = new HashMap<>();

    private SessionManager() {}

    public static SessionManager getInstance() {
        return INSTANCE;
    }

    public String createSession(String userId) {
        String sessionId = UUID.randomUUID().toString();
        sessions.put(sessionId, userId);
        return sessionId;
    }

    public Optional<String> getUserId(String sessionId) {
        if (sessionId == null) return Optional.empty();
        return Optional.ofNullable(sessions.get(sessionId));
    }

    public void invalidate(String sessionId) {
        if (sessionId != null) sessions.remove(sessionId);
    }

    public int activeCount() {
        return sessions.size();
    }
}
