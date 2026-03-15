package com.main;
/**
 * UC-03: User Profile Management
 * 
 * User updates profile information, changes password, or manages preferences.
 * Command Pattern for profile update operations
 * 
 * @author tagr3002
 * @version 3.0
 */
import com.auth.BasicAuthStrategy;
import com.builder.UserBuilder;
import com.exception.ValidationException;
import com.model.User;
import com.model.UserType;
import com.service.AuthService;
import com.service.ProfileService;
import com.service.UserService;
import com.session.SessionManager;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserService();
        try {
            userService.register(
                new UserBuilder()
                    .name("User")
                    .email("user@example.com")
                    .password("Pass@123")
                    .type(UserType.FREE)
            );
        } catch (ValidationException ignored) {}

        AuthService authService = new AuthService(new BasicAuthStrategy(userService));
        Scanner sc = new Scanner(System.in);

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Password: ");
        String password = sc.nextLine().trim();

        Optional<String> sessionId = authService.login(email, password);
        if (sessionId.isEmpty()) {
            System.out.println("Login failed.");
            sc.close();
            return;
        }

        String userId = SessionManager.getInstance().getUserId(sessionId.get()).orElse(null);
        if (userId == null) {
            System.out.println("Session error.");
            sc.close();
            return;
        }

        ProfileService profile = new ProfileService(userService);

        System.out.print("New name (leave blank to skip): ");
        String newName = sc.nextLine().trim();
        if (!newName.isEmpty()) profile.updateName(userId, newName);

        System.out.print("New email (leave blank to skip): ");
        String newEmail = sc.nextLine().trim();
        if (!newEmail.isEmpty()) profile.updateEmail(userId, newEmail);

        System.out.print("New password (leave blank to skip): ");
        String newPwd = sc.nextLine().trim();
        if (!newPwd.isEmpty()) profile.changePassword(userId, newPwd);

        User u = userService.findById(userId);
        System.out.println("Updated:");
        System.out.println("Name: " + u.getName());
        System.out.println("Email: " + u.getEmail());

        sc.close();
    }
}
