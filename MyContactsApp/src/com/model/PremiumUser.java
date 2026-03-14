package com.model;

public class PremiumUser extends User {
    public PremiumUser(String id, String name, String email, String passwordHash) {
        super(id, name, email, passwordHash, UserType.PREMIUM);
    }
}