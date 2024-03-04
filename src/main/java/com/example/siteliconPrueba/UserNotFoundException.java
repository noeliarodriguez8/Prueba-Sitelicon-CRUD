package com.example.siteliconPrueba;

class UserNotFoundException extends RuntimeException {
    UserNotFoundException(Integer userID) {
        super("Could not find user with userID=" + userID);
    }
}
