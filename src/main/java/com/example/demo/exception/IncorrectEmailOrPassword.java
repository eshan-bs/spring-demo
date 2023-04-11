package com.example.demo.exception;

public class IncorrectEmailOrPassword extends Exception {

    public IncorrectEmailOrPassword() {
        super("Incorrect email or password.");
    }
}
//    public IncorrectEmailOrPassword(String msg) {
//        super(msg);
//    }
//
//    public IncorrectEmailOrPassword(String msg, Throwable cause) {
//        super(msg, cause);
//    }
