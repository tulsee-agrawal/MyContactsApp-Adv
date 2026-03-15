package com.view;

import com.model.contacts.Contact;
import com.model.contacts.ContactType;
import com.model.contacts.EmailAddress;
import com.model.contacts.PhoneNumber;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class ContactView {
    private final String id;
    private final String name;
    private final ContactType type;
    private final List<PhoneNumber> phones;
    private final List<EmailAddress> emails;
    private final String notes;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ContactView(String id,
                       String name,
                       ContactType type,
                       List<PhoneNumber> phones,
                       List<EmailAddress> emails,
                       String notes,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.phones = phones == null ? List.of() : List.copyOf(phones);
        this.emails = emails == null ? List.of() : List.copyOf(emails);
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public ContactType getType() { return type; }
    public List<PhoneNumber> getPhones() { return Collections.unmodifiableList(phones); }
    public List<EmailAddress> getEmails() { return Collections.unmodifiableList(emails); }
    public Optional<String> getNotes() { return Optional.ofNullable(notes); }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public static ContactView from(Contact c) {
        return new ContactView(
            c.getId(),
            c.getName(),
            c.getType(),
            c.getPhones(),
            c.getEmails(),
            c.getNotes(),
            c.getCreatedAt(),
            c.getUpdatedAt()
        );
    }
}