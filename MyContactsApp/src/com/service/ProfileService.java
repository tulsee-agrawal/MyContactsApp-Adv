package com.service;

import com.model.User;

public class ProfileService {

    private final UserService userService;

    public ProfileService(UserService userService) {
        this.userService = userService;
    }

    public void updateName(String userId, String newName) {
        User u = userService.findById(userId);
        if (u != null) u.setName(newName);
    }

    public void updateEmail(String userId, String newEmail) {
        User u = userService.findById(userId);
        if (u != null) u.setEmail(newEmail);
    }

    public void changePassword(String userId, String newPassword) {
        User u = userService.findById(userId);
        if (u != null) u.setPassword(newPassword);
    }
}