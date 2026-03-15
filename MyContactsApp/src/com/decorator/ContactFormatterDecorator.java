package com.decorator;

import com.view.ContactView;

public abstract class ContactFormatterDecorator implements ContactFormatter {
    protected final ContactFormatter inner;
    protected ContactFormatterDecorator(ContactFormatter inner) { this.inner = inner; }
    @Override
    public String format(ContactView view) { return inner.format(view); }
}