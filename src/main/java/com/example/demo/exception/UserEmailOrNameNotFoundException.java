package com.example.demo.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserEmailOrNameNotFoundException extends UsernameNotFoundException {

    public UserEmailOrNameNotFoundException() {
        super("Name or Email not found.");
    }
    public UserEmailOrNameNotFoundException(String msg) {
        super(msg);
    }

    public UserEmailOrNameNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
