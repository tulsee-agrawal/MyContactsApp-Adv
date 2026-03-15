package com.decorator;

import com.view.ContactView;

public class PhonesDecorator extends ContactFormatterDecorator {
    public PhonesDecorator(ContactFormatter inner) { super(inner); }

    @Override
    public String format(ContactView v) {
        StringBuilder sb = new StringBuilder(super.format(v));
        if (!v.getPhones().isEmpty()) {
            sb.append("\nPhones: ");
            for (int i = 0; i < v.getPhones().size(); i++) {
                var p = v.getPhones().get(i);
                sb.append(p.getNumber()).append(" (").append(p.getType()).append(")");
                if (i < v.getPhones().size() - 1) sb.append(", ");
            }
        }
        return sb.toString();
    }
}