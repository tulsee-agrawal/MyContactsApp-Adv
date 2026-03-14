package com.service;

import com.builder.UserBuilder;
import com.model.User;

import java.util.HashMap;
import java.util.Map;


public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public User register(UserBuilder builder) {
        User user = builder.build();
        users.put(user.getId(), user);
        return user;
    }

    public User findById(String id) {
        return users.get(id);
    }
}
