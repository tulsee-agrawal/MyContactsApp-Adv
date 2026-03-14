package com.validation;

import com.exception.ValidationException;
import java.util.regex.Pattern;

public final class EmailValidator {
    private EmailValidator() {}

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static void validate(String email) {
        if (email == null || email.trim().isEmpty())
            throw new ValidationException("Email cannot be blank.");
        String e = email.trim();
        if (e.length() > 254)
            throw new ValidationException("Email is too long (max 254).");
        if (!EMAIL_PATTERN.matcher(e).matches())
            throw new ValidationException("Invalid email format.");
    }
}