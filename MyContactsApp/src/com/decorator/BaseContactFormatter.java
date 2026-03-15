package com.decorator;

import com.view.ContactView;

public class BaseContactFormatter implements ContactFormatter {
    @Override
    public String format(ContactView v) {
        return String.format(
            "%s [%s]\nID: %s\nCreated: %s\nUpdated: %s",
            v.getName(),
            v.getType(),
            v.getId(),
            v.getCreatedAt(),
            v.getUpdatedAt()
        );
    }
}