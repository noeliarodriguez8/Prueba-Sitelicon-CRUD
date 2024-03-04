package com.example.siteliconPrueba;

class ContactNotFoundException extends RuntimeException {
    ContactNotFoundException(Integer contactID) {
        super("Could not find contact with contactID=" + contactID);
    }
}
