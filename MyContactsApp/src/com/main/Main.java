package com.main;

import com.auth.BasicAuthStrategy;
import com.builder.ContactBuilder;
import com.builder.UserBuilder;
import com.decorator.BaseContactFormatter;
import com.decorator.ContactFormatter;
import com.decorator.EmailsDecorator;
import com.decorator.NotesDecorator;
import com.decorator.PhonesDecorator;
import com.exception.ValidationException;
import com.model.UserType;
import com.model.contacts.Contact;
import com.model.contacts.ContactType;
import com.model.contacts.PhoneType;
import com.service.AuthService;
import com.service.ContactService;
import com.service.UserService;
import com.session.SessionManager;
import com.view.ContactView;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserService();
        ContactService contactService = new ContactService();

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
        String loginEmail = sc.nextLine().trim();
        System.out.print("Password: ");
        String loginPassword = sc.nextLine().trim();

        Optional<String> sessionId = authService.login(loginEmail, loginPassword);
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

        System.out.print("Contact type [PERSON/ORGANIZATION]: ");
        String t = sc.nextLine().trim().toUpperCase(Locale.ROOT);
        ContactType type = "ORGANIZATION".equals(t) ? ContactType.ORGANIZATION : ContactType.PERSON;

        System.out.print("Contact name: ");
        String name = sc.nextLine().trim();

        System.out.print("Emails (comma-separated, optional): ");
        String emailsLine = sc.nextLine().trim();

        System.out.print("Phones (comma-separated, optional): ");
        String phonesLine = sc.nextLine().trim();

        System.out.print("Notes (optional): ");
        String notes = sc.nextLine().trim();
        if (notes.isEmpty()) notes = null;

        ContactBuilder builder = new ContactBuilder()
                .type(type)
                .name(name)
                .notes(notes);

        if (!emailsLine.isEmpty()) {
            for (String e : emailsLine.split(",")) {
                String v = e.trim();
                if (!v.isEmpty()) builder.addEmail(v);
            }
        }
        if (!phonesLine.isEmpty()) {
            for (String p : phonesLine.split(",")) {
                String v = p.trim();
                if (!v.isEmpty()) builder.addPhone(v, PhoneType.MOBILE);
            }
        }

        try {
            Contact created = contactService.add(userId, builder);
            System.out.println("\nContact created successfully.");
            System.out.println("Contact ID: " + created.getId());

            List<Contact> contacts = contactService.list(userId);
            System.out.println("\nYour contacts (" + contacts.size() + "):");
            for (Contact c : contacts) {
                System.out.print("- " + c.getName() + " [" + c.getType() + "] ");
                System.out.println("#" + c.getId());
            }

            Optional<ContactView> view = contactService.view(userId, created.getId());
            if (view.isPresent()) {
                ContactFormatter formatter =
                    new NotesDecorator(new EmailsDecorator(new PhonesDecorator(new BaseContactFormatter())));
                System.out.println("\nContact details:");
                System.out.println(formatter.format(view.get()));
            } else {
                System.out.println("\nCould not load contact details.");
            }

        } catch (ValidationException ve) {
            System.out.println("Error: " + ve.getMessage());
        }

        sc.close();
    }
}
