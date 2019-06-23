package com.joham.demo.jsonexception;

/**
 * @author joham
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomException() {
        super("Custom exception message.");
    }

}