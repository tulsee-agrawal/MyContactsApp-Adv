package com.builder;

import com.exception.ValidationException;
import com.factory.ContactFactory;
import com.model.contacts.*;
import com.validation.EmailValidator;
import com.validation.PhoneValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactBuilder {
    private String name;
    private final List<PhoneNumber> phones = new ArrayList<>();
    private final List<EmailAddress> emails = new ArrayList<>();
    private String notes;
    private ContactType type = ContactType.PERSON;

    public ContactBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ContactBuilder type(ContactType type) {
        this.type = type;
        return this;
    }

    public ContactBuilder addPhone(String number, PhoneType phoneType) {
        if (number == null) throw new ValidationException("Phone cannot be null.");
        String n = number.trim();
        if (!n.isEmpty()) {
            PhoneValidator.validate(n);
            phones.add(new PhoneNumber(n, phoneType == null ? PhoneType.MOBILE : phoneType));
        }
        return this;
    }

    public ContactBuilder addEmail(String email) {
        if (email == null) return this;
        String e = email.trim();
        if (!e.isEmpty()) {
            EmailValidator.validate(e);
            emails.add(new EmailAddress(e.toLowerCase()));
        }
        return this;
    }

    public ContactBuilder notes(String notes) {
        this.notes = notes == null ? null : notes.trim();
        return this;
    }

    public Contact build() {
        if (name == null || name.trim().isEmpty()) throw new ValidationException("Name cannot be blank.");
        if (type == null) throw new ValidationException("Contact type is required.");
        String id = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        return ContactFactory.create(type, id, name.trim(), phones, emails, notes, now, now);
    }
}