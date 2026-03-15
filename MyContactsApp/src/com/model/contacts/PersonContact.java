package com.model.contacts;

import java.time.LocalDateTime;
import java.util.List;

public class PersonContact extends Contact {
    public PersonContact(String id,
                         String name,
                         List<PhoneNumber> phones,
                         List<EmailAddress> emails,
                         String notes,
                         LocalDateTime createdAt,
                         LocalDateTime updatedAt) {
        super(id, name, phones, emails, notes, ContactType.PERSON, createdAt, updatedAt);
    }
}