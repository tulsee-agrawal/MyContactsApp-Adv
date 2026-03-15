package com.model.contacts;

public class PhoneNumber {
    private final String number;
    private final PhoneType type;

    public PhoneNumber(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public PhoneType getType() {
        return type;
    }
}