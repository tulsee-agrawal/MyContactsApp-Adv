package com.main;

import com.builder.UserBuilder;
import com.exception.ValidationException;
import com.model.User;
import com.model.UserType;
import com.service.UserService;

import java.util.Scanner;
/**
 * 1. User Management Use Cases
 * UC-01: User Registration
 * 
 * User creates an account with email, password, and profile information.
 * Factory Pattern for creating different user types (FreeUser, PremiumUser),
 *  Builder Pattern for User object construction
 *  
 * @author tagr3002
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserService service = new UserService();

        try {
            System.out.print("Enter name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter email: ");
            String email = sc.nextLine().trim();

            System.out.print("Enter password: ");
            String password = sc.nextLine().trim();

            System.out.print("Enter type (FREE or PREMIUM): ");
            String typeInput = sc.nextLine().trim().toUpperCase();

            UserType type = typeInput.equals("PREMIUM") ? UserType.PREMIUM : UserType.FREE;

            User user = service.register(
                    new UserBuilder()
                            .name(name)
                            .email(email)
                            .password(password)
                            .type(type)
            );

            System.out.println("\nUser Registered Successfully!");
            System.out.println("ID   : " + user.getId());
            System.out.println("Name : " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Type : " + user.getType());

        } catch (ValidationException ve) {
            System.out.println("Error: " + ve.getMessage());
        }

        sc.close();
    }
}