package com.ErZet.blog.exception;

public class RecordNotFoundException extends RuntimeException{
    String message;

    public RecordNotFoundException(String message) {
        this.message = message;
    }
}
