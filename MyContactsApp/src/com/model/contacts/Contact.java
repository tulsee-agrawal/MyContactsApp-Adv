package com.model.contacts;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Contact {
    private final String id;
    private final String name;
    private final List<PhoneNumber> phones;
    private final List<EmailAddress> emails;
    private final String notes;
    private final ContactType type;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    protected Contact(String id,
                      String name,
                      List<PhoneNumber> phones,
                      List<EmailAddress> emails,
                      String notes,
                      ContactType type,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.phones = phones;
        this.emails = emails;
        this.notes = notes;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<PhoneNumber> getPhones() { return phones; }
    public List<EmailAddress> getEmails() { return emails; }
    public String getNotes() { return notes; }
    public ContactType getType() { return type; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}