package com.model.contacts;

import java.time.LocalDateTime;
import java.util.List;

public class OrganizationContact extends Contact {
    public OrganizationContact(String id,
                               String name,
                               List<PhoneNumber> phones,
                               List<EmailAddress> emails,
                               String notes,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
        super(id, name, phones, emails, notes, ContactType.ORGANIZATION, createdAt, updatedAt);
    }
}