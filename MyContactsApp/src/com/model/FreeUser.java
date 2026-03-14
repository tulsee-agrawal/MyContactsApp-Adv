package com.model;

public class FreeUser extends User {

    public FreeUser(String id, String name, String email, String passwordHash) {
        super(id, name, email, passwordHash, UserType.FREE);
    }
}