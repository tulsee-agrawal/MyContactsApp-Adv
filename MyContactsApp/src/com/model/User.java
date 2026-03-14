package com.model;

public abstract class User {
    private final String id;
    private final String name;
    private final String email;
    private final String passwordHash; 
    private final UserType type;

    protected User(String id, String name, String email, String passwordHash, UserType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.type = type;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public UserType getType() { return type; }
}
