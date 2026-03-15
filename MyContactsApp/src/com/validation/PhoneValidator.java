package com.validation;

import com.exception.ValidationException;
import java.util.regex.Pattern;

public final class PhoneValidator {
    private PhoneValidator() {}

    private static final Pattern ALLOWED = Pattern.compile("^[+\\d][\\d\\s\\-()]{6,}$");

    public static boolean isValid(String phone) {
        if (phone == null) return false;
        String raw = phone.trim();
        if (!ALLOWED.matcher(raw).matches()) return false;
        int digits = 0;
        for (char c : raw.toCharArray()) if (Character.isDigit(c)) digits++;
        return digits >= 7 && digits <= 15;
    }

    public static void validate(String phone) {
        if (!isValid(phone)) throw new ValidationException("Invalid phone format.");
    }
}