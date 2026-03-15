package com.decorator;

import com.view.ContactView;

public class EmailsDecorator extends ContactFormatterDecorator {
    public EmailsDecorator(ContactFormatter inner) { super(inner); }

    @Override
    public String format(ContactView v) {
        StringBuilder sb = new StringBuilder(super.format(v));
        if (!v.getEmails().isEmpty()) {
            sb.append("\nEmails: ");
            for (int i = 0; i < v.getEmails().size(); i++) {
                var e = v.getEmails().get(i);
                sb.append(e.getValue());
                if (i < v.getEmails().size() - 1) sb.append(", ");
            }
        }
        return sb.toString();
    }
}