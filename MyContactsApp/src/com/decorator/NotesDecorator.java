package com.decorator;

import com.view.ContactView;

public class NotesDecorator extends ContactFormatterDecorator {
    public NotesDecorator(ContactFormatter inner) { super(inner); }

    @Override
    public String format(ContactView v) {
        String base = super.format(v);
        return v.getNotes().map(n -> base + "\nNotes: " + n).orElse(base);
    }
}