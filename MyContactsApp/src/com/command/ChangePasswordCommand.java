package com.command;

import com.model.User;

public class ChangePasswordCommand implements ProfileCommand {
    private final String newRawPassword;

    public ChangePasswordCommand(String newRawPassword) {
        this.newRawPassword = newRawPassword;
    }

    @Override
    public void execute(User user) {
        user.setPassword(newRawPassword);
    }
}