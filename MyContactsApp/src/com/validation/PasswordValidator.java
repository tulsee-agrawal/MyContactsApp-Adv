package com.validation;

import com.exception.ValidationException;
import java.util.regex.Pattern;

/** Strong password: ≥8, upper, lower, digit, special */
public final class PasswordValidator {
    private PasswordValidator() {}

    private static final Pattern STRONG_PASSWORD =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).{8,}$");

    public static void validate(String password) {
        if (password == null || password.trim().isEmpty())
            throw new ValidationException("Password cannot be blank.");
        if (!STRONG_PASSWORD.matcher(password).matches())
            throw new ValidationException(
                "Weak password. Use ≥8 chars with upper, lower, digit, and special character."
            );
    }
}