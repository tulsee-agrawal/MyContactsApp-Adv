package com.command;

import com.model.User;

public class UpdateEmailCommand implements ProfileCommand {
    private final String newEmail;

    public UpdateEmailCommand(String newEmail) {
        this.newEmail = newEmail;
    }

    @Override
    public void execute(User user) {
        user.setEmail(newEmail);
    }
}
