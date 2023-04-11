package com.example.demo.exception;

public class InvalidEmailException extends Exception{
    public InvalidEmailException(){
        super("Invalid email.");
    }
}
