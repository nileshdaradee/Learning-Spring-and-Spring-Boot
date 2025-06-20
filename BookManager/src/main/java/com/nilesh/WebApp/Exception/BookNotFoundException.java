package com.nilesh.WebApp.Exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(int id) {
        super("Book with ID " + id + " not found");
    }
}