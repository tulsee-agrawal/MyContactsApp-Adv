package com.factory;

import com.model.FreeUser;
import com.model.PremiumUser;
import com.model.User;
import com.model.UserType;

public final class UserFactory {
    private UserFactory() {}

    public static User createUser(UserType type,
                                  String name,
                                  String email,
                                  String passwordHash,
                                  String id) {
        switch (type) {
            case PREMIUM:
                return new PremiumUser(id, name, email, passwordHash);
            case FREE:
            default:
                return new FreeUser(id, name, email, passwordHash);
        }
    }
}