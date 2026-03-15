package com.model;

import com.exception.ValidationException;
import com.validation.EmailValidator;
import com.validation.PasswordValidator;
import com.util.PasswordHasher;

public abstract class User {
    private final String id;
    private final UserType type;

    private String name;
    private String email;
    private String passwordHash;

    protected User(String id, String name, String email, String passwordHash, UserType type) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getId() { return id; }
    public UserType getType() { return type; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new ValidationException("Invalid name");
        this.name = name.trim();
    }

    public void setEmail(String email) {
        EmailValidator.validate(email);
        this.email = email.trim().toLowerCase();
    }

    public void setPassword(String rawPassword) {
        PasswordValidator.validate(rawPassword);
        this.passwordHash = PasswordHasher.hash(rawPassword);
    }
}