package com.factory;

import com.model.contacts.*;

import java.time.LocalDateTime;
import java.util.List;

public final class ContactFactory {
    private ContactFactory() {}

    public static Contact create(ContactType type,
                                 String id,
                                 String name,
                                 List<PhoneNumber> phones,
                                 List<EmailAddress> emails,
                                 String notes,
                                 LocalDateTime createdAt,
                                 LocalDateTime updatedAt) {
        switch (type) {
            case ORGANIZATION:
                return new OrganizationContact(id, name, phones, emails, notes, createdAt, updatedAt);
            case PERSON:
            default:
                return new PersonContact(id, name, phones, emails, notes, createdAt, updatedAt);
        }
    }
}
