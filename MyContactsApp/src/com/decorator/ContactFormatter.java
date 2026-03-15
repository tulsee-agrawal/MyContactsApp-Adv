package com.decorator;

import com.view.ContactView;

public interface ContactFormatter {
    String format(ContactView view);
}