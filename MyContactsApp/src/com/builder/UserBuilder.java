package com.builder;

import com.exception.ValidationException;
import com.factory.UserFactory;
import com.model.User;
import com.model.UserType;
import com.util.PasswordHasher;
import com.validation.EmailValidator;
import com.validation.PasswordValidator;

import java.util.UUID;

public class UserBuilder {
    private String name;
    private String email;
    private String rawPassword;
    private UserType type = UserType.FREE; // default

    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder password(String rawPassword) {
        this.rawPassword = rawPassword;
        return this;
    }

    public UserBuilder type(UserType type) {
        this.type = type;
        return this;
    }

    public User build() throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name cannot be blank.");
        }
        EmailValidator.validate(email);
        PasswordValidator.validate(rawPassword);
        if (type == null) {
            throw new ValidationException("User type is required.");
        }

        
        String passwordHash = PasswordHasher.hash(rawPassword);

        
        String id = UUID.randomUUID().toString();

        return UserFactory.createUser(
            type,
            name.trim(),
            email.trim().toLowerCase(),
            passwordHash,
            id
        );
    }
}
