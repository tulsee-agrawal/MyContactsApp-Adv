package com.command;

import com.model.User;

public class UpdateNameCommand implements ProfileCommand {
    private final String newName;

    public UpdateNameCommand(String newName) {
        this.newName = newName;
    }

    @Override
    public void execute(User user) {
        user.setName(newName);
    }
}