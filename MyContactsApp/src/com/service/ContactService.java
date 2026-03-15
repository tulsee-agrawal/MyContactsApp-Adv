package com.service;

import com.builder.ContactBuilder;
import com.model.contacts.Contact;

import java.util.*;

import com.view.ContactView;
import java.util.Optional;


public class ContactService {
    private final Map<String, Map<String, Contact>> store = new HashMap<>();

    public Contact add(String userId, ContactBuilder builder) {
        if (userId == null || userId.trim().isEmpty()) throw new IllegalArgumentException("userId required");
        Contact c = builder.build();
        store.computeIfAbsent(userId, k -> new HashMap<>()).put(c.getId(), c);
        return c;
    }

    public List<Contact> list(String userId) {
        Map<String, Contact> map = store.get(userId);
        if (map == null) return Collections.emptyList();
        return new ArrayList<>(map.values());
    }

    public Contact get(String userId, String contactId) {
        Map<String, Contact> map = store.get(userId);
        if (map == null) return null;
        return map.get(contactId);
    }

    public boolean remove(String userId, String contactId) {
        Map<String, Contact> map = store.get(userId);
        if (map == null) return false;
        return map.remove(contactId) != null;
    }

public Optional<ContactView> view(String userId, String contactId) {
    var map = store.get(userId);
    if (map == null) return Optional.empty();
    var c = map.get(contactId);
    if (c == null) return Optional.empty();
    return Optional.of(ContactView.from(c));
}

}