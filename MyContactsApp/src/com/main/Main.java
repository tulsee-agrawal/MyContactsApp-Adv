package com.main;

import com.builder.UserBuilder;
import com.exception.ValidationException;
import com.model.*;
import com.service.*;
import com.auth.*;

import java.util.Scanner;

import java.util.Optional;
import java.util.Scanner;

public class Main{

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
            System.out.println("Testing user: user@example.com / Pass@123");
        } catch (ValidationException e) {
            System.out.println("Testing failed: " + e.getMessage());
        }
       
        AuthService authService = new AuthService(new BasicAuthStrategy(userService));
        Scanner sc = new Scanner(System.in);

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Password: ");
        String password = sc.nextLine().trim();

        Optional<String> sessionId = authService.login(email, password);

        if (sessionId.isPresent()) {
            System.out.println("\nLogin successful!");
            System.out.println("Session ID: " + sessionId.get());
        } else {
            System.out.println("\nLogin failed.");
        }

        sc.close();
    }
}